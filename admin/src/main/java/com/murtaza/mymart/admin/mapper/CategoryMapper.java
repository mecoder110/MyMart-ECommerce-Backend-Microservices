package com.murtaza.mymart.admin.mapper;

import com.murtaza.mymart.admin.dto.CategoryDto;
import com.murtaza.mymart.admin.entity.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    private static ModelMapper modelMapper;

    @Autowired
    public CategoryMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public static Category toEntity(CategoryDto categoryDto) {
        return modelMapper.map(categoryDto, Category.class);
    }

    public static CategoryDto toDto(Category category) {
        return modelMapper.map(category, CategoryDto.class);
    }
}
