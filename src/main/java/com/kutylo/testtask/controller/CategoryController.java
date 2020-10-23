package com.kutylo.testtask.controller;

import com.kutylo.testtask.dto.request.CategoryRequest;
import com.kutylo.testtask.dto.response.CategoryResponse;
import com.kutylo.testtask.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("categories/")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @ApiOperation(value = "Get all categories")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Categories found", response = CategoryResponse.class)
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<CategoryResponse> findAllCategories() {

        return categoryService.findAllCategories();
    }

    @ApiOperation(value = "Get category by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Category found", response = CategoryResponse.class)
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{id}")
    public CategoryResponse findCategoryById(@PathVariable int id) {

        return categoryService.findCategoryById(id);
    }

    @ApiOperation(value = "Create new category")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Category created", response = CategoryResponse.class)
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CategoryResponse saveNewCategory(@RequestBody CategoryRequest categoryRequest) {
        return categoryService.saveCategory(categoryRequest);
    }


    @ApiOperation(value = "Update category by id")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Category updated", response = CategoryResponse.class),
            @ApiResponse(code = 404, message = "Category not found")
    })
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public CategoryResponse updateCategory(@PathVariable int id, @RequestBody CategoryRequest categoryRequest) {
        return categoryService.updateCategoryById(id, categoryRequest);
    }

    @ApiOperation(value = "Delete category by id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Category deleted", response = CategoryResponse.class),
            @ApiResponse(code = 404, message = "Category not found")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable int id) {

        categoryService.deleteCategoryById(id);
    }
}
