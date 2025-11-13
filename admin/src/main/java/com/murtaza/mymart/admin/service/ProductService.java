package com.murtaza.mymart.admin.service;

import com.murtaza.mymart.admin.dto.CategoryDto;
import com.murtaza.mymart.admin.dto.ProductDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {


    public ProductDto createProduct(Integer categoryId, ProductDto productDto, MultipartFile productImage);

    public ProductDto updateProduct(Integer productId, ProductDto productDto, MultipartFile productImage);

    public ProductDto getProduct(Integer ProductId);

    public List<ProductDto> getProductByCategory(Integer categoryId);

    public boolean deleteProduct(Integer productId);

    public boolean softDeleteProduct(Integer productId);

}
