package com.murtaza.mymart.order.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Order {

    private String email;
    private double totalPrice;
    private int totalQuantity;
}
