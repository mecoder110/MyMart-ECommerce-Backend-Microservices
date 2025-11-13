package com.murtaza.mymart.admin.service;

import com.murtaza.mymart.admin.dto.CategoryDto;
import com.murtaza.mymart.admin.dto.ProductDto;
import com.murtaza.mymart.admin.entity.Category;
import com.murtaza.mymart.admin.entity.Product;
import com.murtaza.mymart.admin.mapper.CategoryMapper;
import com.murtaza.mymart.admin.mapper.ProductMapper;
import com.murtaza.mymart.admin.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.logging.Logger;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryService categoryService;
    @Value("${upload-image-path}")
    private String uploadPath;

    @Override
    public ProductDto createProduct(Integer categoryId, ProductDto productDto, MultipartFile productImage) {
        CategoryDto categoryById = categoryService.getCategoryById(categoryId);
        Category category = CategoryMapper.toEntity(categoryById);
        Product product = ProductMapper.toEntity(productDto);
        product.setCategory(category);
        // Image logic todo
        String originalFilename = productImage.getOriginalFilename();
        Path path = Paths.get(uploadPath + originalFilename);

        if (!Files.exists(path.getParent())) {
            try {
                Files.createDirectories(path.getParent());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            Files.copy(productImage.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        product.setImageUrl(path.toString());
        Product save = productRepository.save(product);
        return ProductMapper.toDto(save);
    }

    @Override
    public ProductDto updateProduct(Integer productId, ProductDto productDto, MultipartFile productImage) {

        boolean isExist = productRepository.existsById(productId);

        Product product = ProductMapper.toEntity(productDto);

        return ProductMapper.toDto(productRepository.save(product));

    }

    @Override
    public ProductDto getProduct(Integer productId) {
        Product product = productRepository.findById(productId).orElseThrow(() ->
                new RuntimeException("Product not found with Id:" + productId));

        return ProductMapper.toDto(product);

    }

    @Override
    public List<ProductDto> getProductByCategory(Integer categoryId) {

        List<Product> productList = productRepository.findByCategoryCategoryId(categoryId);
        return productList.stream().map(product -> {
            return ProductMapper.toDto(product);
        }).toList();

    }

    @Override
    public boolean deleteProduct(Integer productId) {
        Product product = productRepository.findById(productId).orElseThrow(() ->
                new RuntimeException("Product not found with Id:" + productId));
        productRepository.delete(product);
        return true;
    }

    @Override
    public boolean softDeleteProduct(Integer productId) {
        Product product = productRepository.findById(productId).orElseThrow(() ->
                new RuntimeException("Product not found with Id:" + productId));
        product.setActive(false);
        productRepository.save(product);
        return true;
    }
}
