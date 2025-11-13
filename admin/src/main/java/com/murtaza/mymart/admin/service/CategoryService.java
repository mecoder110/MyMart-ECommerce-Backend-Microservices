package com.murtaza.mymart.admin.service;

import com.murtaza.mymart.admin.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

//    Product Category Managment (Create / Update / Retrieve / Delete)

    public CategoryDto createCategory(CategoryDto categoryDto);
    public CategoryDto updateCategory(CategoryDto categoryDto);
    public CategoryDto getCategoryById(Integer categoryId);
    public List<CategoryDto> getAllCategory();
    public boolean deleteCategory(Integer categoryId);
    public boolean softDeleteCategory(Integer categoryId);

}
