package com.murtaza.mymart.order.model;

import lombok.Data;

@Data
public class Order {

    private String email;
    private double totalPrice;
    private int totalQuantity;
}
