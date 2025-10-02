package com.murtaza.mymart.order.model;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDTO {

    private CustomerDTO customerDTO;
    private AddressDTO address;
    private OrderDTO orderDTO;
    private List<OrderItemDTO> orderItemDTOS;
}
