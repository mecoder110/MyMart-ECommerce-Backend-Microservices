package com.murtaza.mymart.admin.dto;

import lombok.Data;

@Data
public class ProductDto {

    private Integer productId;
    private String productName;
    private String description;
    private String title;
    private String imgUrl;
    private double unitPrice;
    private boolean active;
    private Long unitStock;
    private Integer categoryId;
}
