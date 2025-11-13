package com.murtaza.mymart.admin.controller;

import com.murtaza.mymart.admin.dto.ProductDto;
import com.murtaza.mymart.admin.response.ApiResponse;
import com.murtaza.mymart.admin.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/product")

public class AdminProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/create/{categoryId}")
    public ResponseEntity<ApiResponse<ProductDto>> createProduct(@PathVariable Integer categoryId, @RequestBody ProductDto productDto) {
        ProductDto product = productService.createProduct(categoryId, productDto, null);
        ApiResponse body = new ApiResponse();

        if (product != null) {
            body.setStatusCode(200);
            body.setMessage("Product created successful");
            body.setData(product);
            return new ResponseEntity<>(body, HttpStatus.CREATED);
        } else {
            body.setStatusCode(500);
            body.setMessage("Product created failed");
            body.setData(null);
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/update/{productId}")
    public ResponseEntity<ApiResponse<ProductDto>> updateProduct(@PathVariable Integer productId, @RequestBody ProductDto productDto) {
        ProductDto product = productService.updateProduct(productId, productDto, null);
        ApiResponse body = new ApiResponse();

        if (product != null) {
            body.setStatusCode(200);
            body.setMessage("Product update successful");
            body.setData(product);
            return new ResponseEntity<>(body, HttpStatus.OK);
        } else {
            body.setStatusCode(500);
            body.setMessage("Product update failed");
            body.setData(null);
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ApiResponse<ProductDto>> getProduct(@PathVariable Integer productId) {
        ProductDto product = productService.getProduct(productId);

        ApiResponse body = new ApiResponse();

        if (product != null) {
            body.setStatusCode(200);
            body.setMessage("Product found");
            body.setData(product);
            return new ResponseEntity<>(body, HttpStatus.OK);
        } else {
            body.setStatusCode(500);
            body.setMessage("Product not found");
            body.setData(null);
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<ApiResponse<List<ProductDto>>> getProductByCategory(@PathVariable Integer categoryId) {
        List<ProductDto> productByCategory = productService.getProductByCategory(categoryId);

        ApiResponse body = new ApiResponse();

        if (productByCategory.size() != 0) {
            body.setStatusCode(200);
            body.setMessage("Product List");
            body.setData(productByCategory);
            return new ResponseEntity<>(body, HttpStatus.OK);
        } else {
            body.setStatusCode(500);
            body.setMessage("Product List empty");
            body.setData(productByCategory);
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponse<Boolean>> deleteProduct(@PathVariable Integer productId) {
        boolean bool = productService.deleteProduct(productId);


        ApiResponse body = new ApiResponse();

        if (bool) {
            body.setStatusCode(200);
            body.setMessage("Product found");
            body.setData(bool);
            return new ResponseEntity<>(body, HttpStatus.OK);
        } else {
            body.setStatusCode(500);
            body.setMessage("Product not found");
            body.setData(bool);
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{productId}")
    public ResponseEntity<ApiResponse<Boolean>> softDeleteProduct(@PathVariable Integer productId) {
        boolean bool = productService.softDeleteProduct(productId);


        ApiResponse body = new ApiResponse();

        if (bool) {
            body.setStatusCode(200);
            body.setMessage("Product found");
            body.setData(bool);
            return new ResponseEntity<>(body, HttpStatus.OK);
        } else {
            body.setStatusCode(500);
            body.setMessage("Product not found");
            body.setData(bool);
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
