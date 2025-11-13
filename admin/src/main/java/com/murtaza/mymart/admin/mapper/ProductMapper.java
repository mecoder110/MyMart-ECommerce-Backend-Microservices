package com.murtaza.mymart.admin.mapper;

import com.murtaza.mymart.admin.dto.CategoryDto;
import com.murtaza.mymart.admin.dto.ProductDto;
import com.murtaza.mymart.admin.entity.Category;
import com.murtaza.mymart.admin.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    private static ModelMapper modelMapper;

    @Autowired
    public ProductMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public static Product toEntity(ProductDto productDto) {
        return modelMapper.map(productDto, Product.class);
    }

    public static ProductDto toDto(Product product) {
        return modelMapper.map(product, ProductDto.class);
    }
}
