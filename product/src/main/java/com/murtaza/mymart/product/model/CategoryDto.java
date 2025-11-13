package com.murtaza.mymart.product.model;

import lombok.Data;

@Data
public class CategoryDto {

    private Integer categoryId;
    private String categoryName;
    private boolean active;
}
