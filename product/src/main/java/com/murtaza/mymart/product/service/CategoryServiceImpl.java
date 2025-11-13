package com.murtaza.mymart.product.service;

import com.murtaza.mymart.product.entity.Category;
import com.murtaza.mymart.product.exception.NoResourceFoundException;
import com.murtaza.mymart.product.model.CategoryDto;
import com.murtaza.mymart.product.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CategoryDto> getAllCategory() {
        return categoryRepository.findAll().stream().map(category ->
                modelMapper.map(category, CategoryDto.class)).toList();
    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() ->
                new NoResourceFoundException("Category", "Resource not found with Id", categoryId, 404));

        return modelMapper.map(category, CategoryDto.class);
    }
}
