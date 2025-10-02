package com.murtaza.mymart.order.entity;

import java.util.List;


public class OrderRequest {


    private Integer id;

    private Customer customer;
    private Address address;
    private Order order;
    private List<OrderItem> orderItems;
}
