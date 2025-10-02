package com.murtaza.mymart.order.model;

import lombok.Data;

@Data
public class OrderDTO {

    private String email;
    private double totalPrice;
    private int totalQuantity;
}
