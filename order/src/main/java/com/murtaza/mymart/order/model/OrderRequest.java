package com.murtaza.mymart.order.model;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {

    private Customer customer;
    private Address address;
    private Order order;
    private List<OrderItem> orderItems;
}
