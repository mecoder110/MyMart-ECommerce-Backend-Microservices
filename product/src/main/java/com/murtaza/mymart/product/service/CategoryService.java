package com.murtaza.mymart.product.service;

import com.murtaza.mymart.product.model.CategoryDto;

import java.util.List;

public interface CategoryService {

    public List<CategoryDto> getAllCategory();

    public CategoryDto getCategoryById(Integer categoryId);
}
