package com.murtaza.mymart.admin.service;

import com.murtaza.mymart.admin.dto.CategoryDto;
import com.murtaza.mymart.admin.entity.Category;
import com.murtaza.mymart.admin.mapper.CategoryMapper;
import com.murtaza.mymart.admin.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = CategoryMapper.toEntity(categoryDto);
        category.setActive(true);
        return CategoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto) {
        Category category = CategoryMapper.toEntity(categoryDto);
        return CategoryMapper.toDto(categoryRepository.save(category));

    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() ->
                new RuntimeException("Category not found with Id: " + categoryId));
        return CategoryMapper.toDto(category);

    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> all = categoryRepository.findAll();
        return all.stream().map(category -> {
            return CategoryMapper.toDto(category);
        }).toList();

    }

    @Override
    public boolean deleteCategory(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() ->
                new RuntimeException("Category not found with Id: " + categoryId));
        categoryRepository.delete(category);
        return true;
    }

    @Override
    public boolean softDeleteCategory(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() ->
                new RuntimeException("Category not found with Id: " + categoryId));
        category.setActive(false);
        categoryRepository.save(category);
        return true;
    }
}
