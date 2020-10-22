package com.kutylo.testtask.service;

import com.kutylo.testtask.dto.request.CategoryRequest;
import com.kutylo.testtask.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> findAllCategories();

    CategoryResponse findCategoryById(int id);

    CategoryResponse saveCategory(CategoryRequest categoryRequest);

    CategoryResponse updateCategoryById(int id, CategoryRequest categoryRequest);

    void deleteCategoryById(int id);
}
