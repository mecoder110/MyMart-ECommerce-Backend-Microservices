package com.murtaza.mymart.order.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Setter
@Getter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private double totalPrice;
    private int totalQuantity;

    private String razorpayOrderId;
    private String orderStatus;
    private String orderTrackingNumber;
}
