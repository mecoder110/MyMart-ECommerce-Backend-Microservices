package com.murtaza.mymart.product.controller;

import com.murtaza.mymart.product.response.APIResponse;
import com.murtaza.mymart.product.model.CategoryDto;
import com.murtaza.mymart.product.model.ProductDto;
import com.murtaza.mymart.product.service.CategoryService;
import com.murtaza.mymart.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product-api/")
public class ProductController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    @GetMapping("getCategories/")
    public ResponseEntity<APIResponse<CategoryDto>> getCategories() {
        List<CategoryDto> categories = categoryService.getAllCategory();
        APIResponse body = new APIResponse();
        body.setMessage("Category List Found");
        body.setStatusCode(200);
        body.setData(categories);
        return new ResponseEntity<>(body, HttpStatus.FOUND);
    }

    @GetMapping("getCategories/{categoryId}")
    public ResponseEntity<APIResponse<CategoryDto>> getCategoryById(@PathVariable Integer categoryId) {
        CategoryDto category = categoryService.getCategoryById(categoryId);
        APIResponse body = new APIResponse();
        body.setMessage("Category Found");
        body.setStatusCode(200);
        body.setData(category);
        return new ResponseEntity<>(body, HttpStatus.FOUND);
    }
    //============ Product ===============//

    @GetMapping("getProducts/{categoryId}")
    public ResponseEntity<APIResponse<CategoryDto>> getProdtuctByCategory(@PathVariable Integer categoryId) {
        List<ProductDto> productDtos = productService.allProductByCategoryId(categoryId);
        APIResponse body = new APIResponse();
        body.setMessage("Product List Found");
        body.setStatusCode(200);
        body.setData(productDtos);
        return new ResponseEntity<>(body, HttpStatus.FOUND);
    }

    @GetMapping("getProducts/")
    public ResponseEntity<APIResponse<CategoryDto>> getAllProdtuct() {
        List<ProductDto> productDtos = productService.allProduct();
        APIResponse body = new APIResponse();
        body.setMessage("Product List Found");
        body.setStatusCode(200);
        body.setData(productDtos);
        return new ResponseEntity<>(body, HttpStatus.FOUND);
    }

    @GetMapping("searchProducts/{keyword}")
    public ResponseEntity<APIResponse<CategoryDto>> searchProdtucts(@PathVariable String keyword) {
        List<ProductDto> productDtos = productService.allProductByKeywords(keyword);
        APIResponse body = new APIResponse();
        body.setMessage("Product List Found");
        body.setStatusCode(200);
        body.setData(productDtos);
        return new ResponseEntity<>(body, HttpStatus.FOUND);
    }

    @GetMapping("getProduct/{productId}")
    public ResponseEntity<APIResponse<CategoryDto>> getProductById(@PathVariable Integer productId) {
        ProductDto productDto = productService.productByProductId(productId);
        APIResponse body = new APIResponse();
        body.setMessage("Product List Found");
        body.setStatusCode(200);
        body.setData(productDto);
        return new ResponseEntity<>(body, HttpStatus.FOUND);
    }

    // admin
    @PostMapping("Product/save-all")
    public ResponseEntity<APIResponse<Boolean>> saveAllProduct(@RequestBody List<ProductDto> productDtos) {

        boolean bool = productService.saveAllProduct(productDtos);

        APIResponse body = new APIResponse();
        body.setMessage("Bulk Product Save");
        body.setStatusCode(200);
        body.setData(bool);
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }


}
