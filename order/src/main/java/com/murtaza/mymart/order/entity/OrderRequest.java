package com.murtaza.mymart.order.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class OrderRequest {

    private Customer customer;
    private Address address;
    private Order order;
    private List<OrderItem> orderItems;
}
