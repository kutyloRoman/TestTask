package com.kutylo.testtask.service.impl;

import com.kutylo.testtask.dto.request.CategoryRequest;
import com.kutylo.testtask.dto.response.CategoryResponse;
import com.kutylo.testtask.mapper.ProductMapper;
import com.kutylo.testtask.repository.ProductRepository;
import com.kutylo.testtask.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<CategoryResponse> findAllCategories() {
        return null;
    }

    @Override
    public CategoryResponse findCategoryById(int id) {
        return null;
    }

    @Override
    public CategoryResponse saveCategory(CategoryRequest categoryRequest) {
        return null;
    }

    @Override
    public CategoryResponse updateCategoryById(int id, CategoryRequest categoryRequest) {
        return null;
    }

    @Override
    public void deleteCategoryById(int id) {

    }
}
