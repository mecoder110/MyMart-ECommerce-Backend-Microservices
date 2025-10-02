package com.murtaza.mymart.order.model;

import lombok.Data;

@Data
public class OrderItemDTO {

    private int quantity;
    private double unitPrice;
    private String imageUrl;
    private String productName;
}
