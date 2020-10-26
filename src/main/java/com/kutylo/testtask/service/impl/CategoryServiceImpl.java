package com.kutylo.testtask.service.impl;

import com.kutylo.testtask.dto.request.CategoryRequest;
import com.kutylo.testtask.dto.response.CategoryResponse;
import com.kutylo.testtask.mapper.CategoryMapper;
import com.kutylo.testtask.model.Category;
import com.kutylo.testtask.repository.CategoryRepository;
import com.kutylo.testtask.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponse> findAllCategories() {

        log.info("Getting list of all categories");

        return categoryRepository.findAll().stream()
                .map(categoryMapper::modelToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponse findCategoryById(int id) {
        log.info("Getting category by id:" + id);

        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Not found category with id:" + id);
                    return new EntityNotFoundException("Not found category with id:" + id);
                });

        return categoryMapper.modelToResponse(category);
    }

    @Override
    public CategoryResponse saveCategory(CategoryRequest categoryRequest) {
        log.info("Saving category = " + categoryRequest);

        Category category = categoryMapper.requestToModel(categoryRequest);
        categoryRepository.save(category);

        return categoryMapper.modelToResponse(category);
    }

    @Override
    public CategoryResponse updateCategoryById(int id, CategoryRequest categoryRequest) {
        log.info("Updating category with id:" + id);

        Category category = categoryMapper.requestToModel(categoryRequest);
        category.setId(id);
        categoryRepository.save(category);

        return categoryMapper.modelToResponse(category);
    }

    @Override
    public void deleteCategoryById(int id) {
        log.info("Deleting category with id:" + id);
        if (!categoryRepository.existsById(id)) {
            log.error("Category with id = " + id + " not found");
            throw new EntityNotFoundException("Category with id = " + id + " not found");
        }

        categoryRepository.deleteById(id);

    }
}
