package com.murtaza.mymart.order.service;

import com.murtaza.mymart.order.entity.CustomerEntity;
import com.murtaza.mymart.order.entity.Order;
import com.murtaza.mymart.order.entity.OrderItem;
import com.murtaza.mymart.order.entity.ShippingAddress;
import com.murtaza.mymart.order.mapper.AddressMapper;
import com.murtaza.mymart.order.mapper.CustomerMapper;
import com.murtaza.mymart.order.mapper.OrderItemMapper;
import com.murtaza.mymart.order.mapper.OrderMapper;
import com.murtaza.mymart.order.model.*;
import com.murtaza.mymart.order.repository.AddressRepository;
import com.murtaza.mymart.order.repository.CustomerRepository;
import com.murtaza.mymart.order.repository.OrderItemRepository;
import com.murtaza.mymart.order.repository.OrderRepository;
import com.razorpay.RazorpayException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private RazorPayClientService razorPayClientService;

    @Override
    public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO) throws Exception {
        log.info("== Create OrderRequest");
        CustomerDTO customerDTO = orderRequestDTO.getCustomerDTO();

        CustomerEntity customer = customerRepository.findByEmail(customerDTO.getEmail());
        AddressDTO addressDTO = orderRequestDTO.getAddressDTO();
        ShippingAddress address = AddressMapper.toEntity(addressDTO);

        // if new customer
        if (customer == null) {
            log.info("== new Customer");
            customer = CustomerMapper.toEntity(customerDTO);
            customer = customerRepository.save(customer);

            address.setCustomer(customer); // ASSOCIATE MAPPING
            address = addressRepository.save(address);
        } else {
            if (addressDTO.getAddressId() == null) {
                log.info("== new Address");
                address.setCustomer(customer); // ASSOCIATE MAPPING
                address = addressRepository.save(address);
            } else {
                address = addressRepository.findById(addressDTO.getAddressId()).get();
            }
        }
        OrderDTO orderDTO = orderRequestDTO.getOrderDTO();
        Order order = OrderMapper.toEntity(orderDTO);
        // build Order
        String razorpayOrderId = razorPayClientService.createPayment(orderDTO.getTotalPrice(), "INR", orderDTO.getCustomerEmail());
        log.info("== Razorpay OrderId" + razorpayOrderId);
        order.setRazorpayOrderId(razorpayOrderId);
        order.setOrderStatus("CREATED");
        order.setOrderTrackingNum(generateTrackingNum());
        order.setPaymentStatus("PENDING");

        order.setCustomerEntity(customer); // ASSOCIATE MAPPING
        order.setShippingAddress(address); // ASSOCIATE MAPPING
        Order save = orderRepository.save(order);
        log.info("== Order Created");
        // orderItemDto to entity
        List<OrderItem> list = orderRequestDTO.getOrderItemDTOList().stream()
                .map(itemDto -> {
                    OrderItem entity = OrderItemMapper.toEntity(itemDto);
                    entity.setOrder(save); // ASSOCIATE MAPPING
                    return entity;
                }).toList();

        //save orderItem
        orderItemRepository.saveAll(list);

        // Preparing Final Order Response
        OrderResponseDTO orderResponse = new OrderResponseDTO();
        orderResponse.setOrderStatus(save.getOrderStatus());
        orderResponse.setRazorpayOrderId(save.getRazorpayOrderId());
        orderResponse.setOrderTrackingNumber(save.getOrderTrackingNum());
        orderResponse.setPaymentStatus(save.getPaymentStatus());
        return orderResponse;
    }

    @Override
    public OrderResponseDTO updateOrder(UpdateOrderDTO updateOrderDTO) {

        Order order = orderRepository.findByOrderTrackingNum(updateOrderDTO.getOrderTrackingNum());
        log.info("== " + updateOrderDTO.getOrderTrackingNum());

        if (order == null) {
            throw new RuntimeException("No Order Found");
        }
        if (updateOrderDTO.getRazorpayPaymentId() != null) {
            order.setOrderStatus("CONFIRMED");
            order.setPaymentStatus("COMPLETED");
            order.setRazorpayPaymentId(updateOrderDTO.getRazorpayPaymentId());
            order = orderRepository.save(order);
        }

        OrderResponseDTO orderResponse = new OrderResponseDTO();
        orderResponse.setRazorpayOrderId(order.getRazorpayOrderId());
        orderResponse.setOrderStatus(order.getOrderStatus());
        orderResponse.setOrderTrackingNumber(order.getOrderTrackingNum());
        orderResponse.setPaymentStatus(order.getPaymentStatus());
        orderResponse.setRazorpayPaymentId(order.getRazorpayPaymentId());
        return orderResponse;
    }

    @Override
    public OrderResponseDTO cancleOrder(String orderTrackingNumber) throws RazorpayException {
        Order order = orderRepository.findByOrderTrackingNum(orderTrackingNumber);

        String status = razorPayClientService.refundPayment(order.getRazorpayPaymentId(), order.getTotalPrice().intValue()*100);

        order.setOrderStatus("CANCELED");
        order.setPaymentStatus("REFUND IN PROCESS");
        order.setDeliveyDate(null);
        order = orderRepository.save(order);

        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
        orderResponseDTO.setRazorpayOrderId(order.getRazorpayOrderId());
        orderResponseDTO.setOrderTrackingNumber(orderTrackingNumber);
        orderResponseDTO.setOrderStatus("CANCELED");
        orderResponseDTO.setRazorpayPaymentId(order.getRazorpayPaymentId());
        return orderResponseDTO;
    }

    @Override
    public List<OrderDTO> getCustomerOrders(String customerEmail) {
        List<Order> list = orderRepository.findByCustomerEmail(customerEmail);
        return list.stream().map(OrderMapper::toDto).toList();
    }

    private String generateTrackingNum() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String datePart = sdf.format(new Date());
        String randomStr = UUID.randomUUID().toString().substring(0, 5).toUpperCase();
        return "OD" + datePart + randomStr;
    }
}

