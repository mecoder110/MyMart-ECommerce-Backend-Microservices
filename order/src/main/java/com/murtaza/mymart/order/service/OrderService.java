package com.murtaza.mymart.order.service;

import com.murtaza.mymart.order.model.OrderDTO;
import com.murtaza.mymart.order.model.OrderRequestDTO;
import com.murtaza.mymart.order.model.OrderResponseDTO;
import com.murtaza.mymart.order.model.UpdateOrderDTO;
import com.razorpay.RazorpayException;

import java.util.List;

public interface OrderService {

    public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO) throws Exception;

    public OrderResponseDTO updateOrder(UpdateOrderDTO updateOrderDTO);

    public OrderResponseDTO cancleOrder(String orderTrackingNumber) throws RazorpayException;

    public List<OrderDTO> getCustomerOrders(String customerEmail);
}
