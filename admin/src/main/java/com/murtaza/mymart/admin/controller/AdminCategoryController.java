package com.murtaza.mymart.admin.controller;

import com.murtaza.mymart.admin.dto.CategoryDto;
import com.murtaza.mymart.admin.response.ApiResponse;
import com.murtaza.mymart.admin.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/category")

public class AdminCategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<CategoryDto>> createCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto category = categoryService.createCategory(categoryDto);
        ApiResponse body = new ApiResponse();

        if (category != null) {
            body.setStatusCode(200);
            body.setMessage("Category created successful");
            body.setData(category);
            return new ResponseEntity<>(body, HttpStatus.CREATED);
        } else {
            body.setStatusCode(500);
            body.setMessage("Category created failed");
            body.setData(null);
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<ApiResponse<CategoryDto>> updateCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto category = categoryService.updateCategory(categoryDto);
        ApiResponse body = new ApiResponse();

        if (category != null) {
            body.setStatusCode(200);
            body.setMessage("Category update successful");
            body.setData(category);
            return new ResponseEntity<>(body, HttpStatus.OK);
        } else {
            body.setStatusCode(500);
            body.setMessage("Category update failed");
            body.setData(null);
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<ApiResponse<CategoryDto>> getCategoryById(@PathVariable Integer categoryId) {
        CategoryDto category = categoryService.getCategoryById(categoryId);
        ApiResponse body = new ApiResponse();

        if (category != null) {
            body.setStatusCode(200);
            body.setMessage("Category found");
            body.setData(category);
            return new ResponseEntity<>(body, HttpStatus.FOUND);
        } else {
            body.setStatusCode(500);
            body.setMessage("Category not found");
            body.setData(null);
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<List<CategoryDto>>> getAllCategory() {
        List<CategoryDto> allCategory = categoryService.getAllCategory();

        ApiResponse body = new ApiResponse();

        if (allCategory.size() != 0) {
            body.setStatusCode(200);
            body.setMessage("Category List");
            body.setData(allCategory);
            return new ResponseEntity<>(body, HttpStatus.FOUND);
        } else {
            body.setStatusCode(500);
            body.setMessage("Category List empty []");
            body.setData(allCategory);
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{delete}")
    public ResponseEntity<ApiResponse<Boolean>> delete(@PathVariable Integer categoryId) {
        boolean bool = categoryService.deleteCategory(categoryId);

        ApiResponse body = new ApiResponse();

        if (bool) {
            body.setStatusCode(200);
            body.setMessage("Category deleted successful");
            body.setData(bool);
            return new ResponseEntity<>(body, HttpStatus.OK);
        } else {
            body.setStatusCode(500);
            body.setMessage("Category deleted failed");
            body.setData(bool);
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{softDelete}")
    public ResponseEntity<ApiResponse<Boolean>> softDelete(@PathVariable Integer categoryId) {
        boolean bool = categoryService.deleteCategory(categoryId);

        ApiResponse body = new ApiResponse();

        if (bool) {
            body.setStatusCode(200);
            body.setMessage("Category soft deleted successful");
            body.setData(bool);
            return new ResponseEntity<>(body, HttpStatus.OK);
        } else {
            body.setStatusCode(500);
            body.setMessage("Category soft deleted failed");
            body.setData(bool);
            return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
