package com.murtaza.mymart.order.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class OrderItem {

    private int quantity;
    private double unitPrice;
    private String imageUrl;
    private String productName;
}
