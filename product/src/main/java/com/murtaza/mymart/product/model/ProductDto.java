package com.murtaza.mymart.product.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ProductDto {

    private Integer productId;
    private String productName;
    private String description;
    private String title;
    private BigDecimal unitPrice;
    private String imageUrl;
    private boolean active;
    private int unitInStock;
    private LocalDate dateCreated;
    private LocalDate lastUpdated;
    private Integer categoryId;
}
