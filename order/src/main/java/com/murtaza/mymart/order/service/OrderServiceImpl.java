package com.murtaza.mymart.order.service;


import com.murtaza.mymart.order.entity.Address;
import com.murtaza.mymart.order.entity.Customer;
import com.murtaza.mymart.order.entity.Order;
import com.murtaza.mymart.order.entity.OrderItem;
import com.murtaza.mymart.order.model.OrderRequestDTO;
import com.murtaza.mymart.order.model.OrderResponseDTO;
import com.murtaza.mymart.order.model.UpdateOrderDTO;
import com.murtaza.mymart.order.repository.AddressRepository;
import com.murtaza.mymart.order.repository.CustomerRepository;
import com.murtaza.mymart.order.repository.OrderItemRepository;
import com.murtaza.mymart.order.repository.OrderRepository;
import com.razorpay.RazorpayClient;
import lombok.extern.java.Log;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Log
public class OrderServiceImpl implements OrderService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderItemRepository orderItemRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Value("${razorpay.key.id}")
    private String razorpayKeyId;

    @Value("${razorpay.key.secret}")
    private String razorpayKeySecret;

    public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO) {
        // save customer
        Customer customerDb = customerRepository.save(modelMapper
                .map(orderRequestDTO.getCustomerDTO(), Customer.class));
        // save shipping address
        Address addressDb = addressRepository.save(modelMapper
                .map(orderRequestDTO.getAddress(), Address.class));

        Order order = modelMapper.map(orderRequestDTO.getOrderDTO(), Order.class);
        try {
            Order fromRazor = createPayment(order.getTotalPrice(), "INR", order.getEmail());
            order.setOrderStatus(fromRazor.getOrderStatus());
            order.setRazorpayOrderId(fromRazor.getRazorpayOrderId());
            order.setOrderTrackingNumber(fromRazor.getOrderTrackingNumber());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Order orderDb = orderRepository.save(order);
        List<OrderItem> orderItemList = orderRequestDTO.getOrderItemDTOS().stream().map(item ->
                modelMapper.map(item, OrderItem.class)).toList();
        List<OrderItem> orderItemsDb = orderItemRepository.saveAll(orderItemList);

        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
        orderResponseDTO.setRazorpayOrderId(order.getRazorpayOrderId());
        orderResponseDTO.setOrderStatus(order.getOrderStatus());
        orderResponseDTO.setOrderTrackingNumber(order.getOrderTrackingNumber());

        return orderResponseDTO;
    }

    public boolean updateOrder(UpdateOrderDTO updateOrderDTO) {
        Order order = orderRepository.findByRazorpayOrderId(updateOrderDTO.getRazorpayOrderId());
        order.setOrderStatus("PAID");
        orderRepository.save(order);
        return true;
    }

    public boolean cancleOrder(UpdateOrderDTO updateOrderDTO) {
        Order order = orderRepository.findByRazorpayOrderId(updateOrderDTO.getRazorpayOrderId());
        order.setOrderStatus("CANCLED");
        orderRepository.save(order);
        return true;
    }

    private Order createPayment(double amount, String currency, String receipt) throws Exception {
        RazorpayClient razorpayClient = new RazorpayClient(razorpayKeyId, razorpayKeySecret);

        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", amount * 100); // amount in paise (INR 100 = 10000 paise)
        orderRequest.put("currency", currency);
        orderRequest.put("receipt", receipt);

        com.razorpay.Order order = razorpayClient.orders.create(orderRequest);

        log.info(order.toString());

        Order orderEntity = new Order();

        orderEntity.setRazorpayOrderId(order.get("id"));
        orderEntity.setOrderStatus(order.get("status"));
        String trackingId = UUID.randomUUID().toString();

        log.info("tracking id === : " + trackingId);
        orderEntity.setOrderTrackingNumber(trackingId);

        return orderEntity;
    }
}

