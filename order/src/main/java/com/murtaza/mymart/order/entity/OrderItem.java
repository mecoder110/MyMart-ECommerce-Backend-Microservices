package com.murtaza.mymart.order.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int quantity;
    private double unitPrice;
    private String imageUrl;
    private String productName;

    private Integer product_id;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;


}
