package com.murtaza.mymart.order.service;

import com.murtaza.mymart.order.model.OrderRequestDTO;
import com.murtaza.mymart.order.model.OrderResponseDTO;
import com.murtaza.mymart.order.model.UpdateOrderDTO;

public interface OrderService {

    public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO);

    public boolean updateOrder(UpdateOrderDTO updateOrderDTO);

    public boolean cancleOrder(UpdateOrderDTO updateOrderDTO);
}
