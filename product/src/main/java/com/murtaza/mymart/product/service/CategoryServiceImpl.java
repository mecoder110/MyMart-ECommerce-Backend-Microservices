package com.murtaza.mymart.product.service;

import com.murtaza.mymart.product.entity.Category;
import com.murtaza.mymart.product.exception.NoResourceFoundException;
import com.murtaza.mymart.product.model.CategortDto;
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
    public List<CategortDto> getCategories() {
        return categoryRepository.findAll().stream().map(category ->
                modelMapper.map(category, CategortDto.class)).toList();
    }

    @Override
    public CategortDto getCategoryById(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() ->
                new NoResourceFoundException("Category", "Resource not found with Id", categoryId, 404));

        return modelMapper.map(category, CategortDto.class);
    }
}
