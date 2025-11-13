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
    private Integer itemId;
    private String imageUrl;
    private Integer quantity;
    private Double unitPrice;
    private String productName;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;


}
