package com.murtaza.mymart.product.service;

import com.murtaza.mymart.product.model.CategortDto;

import java.util.List;

public interface CategoryService {

    public List<CategortDto> getCategories();

    public CategortDto getCategoryById(Integer categoryId);
}
