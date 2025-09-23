package com.murtaza.mymart.product.service;

import com.murtaza.mymart.product.entity.Product;
import com.murtaza.mymart.product.exception.NoResourceFoundException;
import com.murtaza.mymart.product.model.ProductDto;
import com.murtaza.mymart.product.repository.ProductRepository;
import lombok.extern.java.Log;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ProductDto> allProduct() {
        return productRepository.findAll().stream().map(product ->
                modelMapper.map(product, ProductDto.class)).toList();
    }

    @Override
    public List<ProductDto> allProductByCategoryId(Integer categoryId) {
        return productRepository.findByCategoryCategoryId(categoryId).stream().map(product ->
                modelMapper.map(product, ProductDto.class)).toList();
    }

    @Override
    public List<ProductDto> allMatchByKeywords(String keyword) {
        List<Product> filterProduct = productRepository.findByProductNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrTitleContainingIgnoreCase(keyword, keyword, keyword);
        return filterProduct.stream().map(product ->
                modelMapper.map(product, ProductDto.class)).toList();
    }

    @Override
    public ProductDto productByProductId(Integer productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new NoResourceFoundException("Product", "No Resource Found with : ", productId, 404));

        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public boolean saveAllProduct(List<ProductDto> productDtos) {


        List<Product> list = productDtos.stream().map(dtos ->
                modelMapper.map(dtos, Product.class)
        ).toList();
        log.info("==> " + list.size());
        List<Product> products = productRepository.saveAll(list);
        return true;
    }
}
