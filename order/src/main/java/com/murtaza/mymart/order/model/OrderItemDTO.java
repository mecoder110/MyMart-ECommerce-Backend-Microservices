package com.murtaza.mymart.order.model;

import lombok.Data;

@Data
public class OrderItemDTO {

    private Integer itemId;
    private String imageUrl;
    private Integer quantity;
    private Double unitPrice;
    private String productName;
}
