package com.murtaza.mymart.order.model;

import lombok.Data;

@Data
public class OrderItem {

    private int quantity;
    private double unitPrice;
    private String imageUrl;
    private String productName;
}
