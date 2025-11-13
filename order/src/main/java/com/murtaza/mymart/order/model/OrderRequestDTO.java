package com.murtaza.mymart.order.model;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDTO {

    private CustomerDTO customerDTO;
    private AddressDTO addressDTO;
    private OrderDTO orderDTO;
    private List<OrderItemDTO> orderItemDTOList;
}
