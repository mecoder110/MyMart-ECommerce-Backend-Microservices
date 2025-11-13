package com.murtaza.mymart.product.service;

import com.murtaza.mymart.product.model.ProductDto;

import java.util.List;

public interface ProductService {

    public List<ProductDto> allProduct();

    public List<ProductDto> allProductByCategoryId(Integer categoryId);

    public List<ProductDto> allProductByKeywords(String keyword);

    public ProductDto productByProductId(Integer productId);

    public boolean saveAllProduct(List<ProductDto> productDtos);

}
