package com.kutylo.testtask.service;


import com.kutylo.testtask.dto.request.CategoryRequest;
import com.kutylo.testtask.dto.response.CategoryResponse;
import com.kutylo.testtask.mapper.CategoryMapper;
import com.kutylo.testtask.model.Category;
import com.kutylo.testtask.repository.CategoryRepository;
import com.kutylo.testtask.service.impl.CategoryServiceImpl;
import org.junit.Before;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class CategoryServiceTest {

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Mock
    private CategoryRepository categoryRepositoryMock;

    @Mock
    private CategoryMapper categoryMapperMock;

    private Category category;
    private CategoryRequest categoryRequest;


    @Before
    public void setUpMocks() {
        MockitoAnnotations.initMocks(this);

        category = new Category(
                523,
                "name",
                "description",
                null
        );

        categoryRequest = new CategoryRequest(
                "name",
                "description"
        );

        Mockito.when(categoryMapperMock.requestToModel(Mockito.any(CategoryRequest.class)))
                .thenAnswer(invocationOnMock -> {
                    CategoryRequest categoryRequest = invocationOnMock.getArgument(0);
                    return new Category(
                            0,
                            categoryRequest.getName(),
                            categoryRequest.getDescription(),
                            null
                    );
                });
        when(categoryMapperMock.modelToResponse(Mockito.any(Category.class)))
                .thenAnswer(invocationOnMock -> {
                    Category category = invocationOnMock.getArgument(0);
                    return new CategoryResponse(
                            category.getId(),
                            category.getName(),
                            category.getDescription()
                    );
                });

        when(categoryMapperMock.requestToEntity(Mockito.any(CategoryRequest.class), Mockito.any(Category.class)))
                .thenAnswer(invocationOnMock -> {
                    CategoryRequest categoryRequest = invocationOnMock.getArgument(0);
                    Category category = invocationOnMock.getArgument(1);
                    category.setName(categoryRequest.getName());
                    category.setDescription(categoryRequest.getDescription());
                    return category;
                });

        when(categoryRepositoryMock.findAll())
                .thenReturn(Collections.singletonList(category));
    }

    @Test
    public void findAllCategoriesTest() {
        List<CategoryResponse> answer = categoryService.findAllCategories();

        assertTrue(answer.size() > 0);
        assertEquals(answer.get(0).getId(), category.getId());
        assertEquals(answer.get(0).getName(), category.getName());
    }

    @Test
    public void findCategoryByIdTest() {
        int id = 23;

        when(categoryRepositoryMock.findById(id))
                .thenAnswer(invocationOnMock -> {
                    Category category1 = category;
                    category1.setId(invocationOnMock.getArgument(0));
                    return Optional.of(category1);
                });

        CategoryResponse answer = categoryService.findCategoryById(id);

        assertNotNull(answer);
        assertEquals(answer.getId(), id);
        assertEquals(answer.getName(), category.getName());
    }

    @Test
    public void findCategoryByIncorrectIdTest() {
        int id = 23;

        assertThrows(EntityNotFoundException.class, () -> categoryService.findCategoryById(id));
    }

    @Test
    public void saveCategoryTest() {
        int generatedId = 23;
        CategoryRequest testCategory = categoryRequest;
        category.setName("name2");

        when(categoryRepositoryMock.save(Mockito.any(Category.class)))
                .thenAnswer(invocationOnMock -> {
                    Category category = invocationOnMock.getArgument(0);
                    category.setId(generatedId);
                    return category;
                });

        CategoryResponse answer = categoryService.saveCategory(testCategory);

        assertNotNull(answer);
        assertEquals(generatedId, answer.getId());
        assertEquals(answer.getName(), testCategory.getName());
    }

    @Test
    public void updateCategoryByIdTest() {
        int id = 777;
        when(categoryRepositoryMock.findAll())
                .thenReturn(new ArrayList<>());

        when(categoryRepositoryMock.findById(id))
                .thenAnswer(invocationOnMock -> {
                    Category category1 = category;
                    category1.setId(invocationOnMock.getArgument(0));
                    return Optional.of(category1);
                });

        when(categoryRepositoryMock.save(Mockito.any(Category.class)))
                .thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        CategoryResponse answer = categoryService.updateCategoryById(id, categoryRequest);

        assertNotNull(answer);
        assertEquals(answer.getId(), id);
        assertEquals(answer.getName(), categoryRequest.getName());
    }

    @Test
    public void deleteCategoryByIdTest() {
        int id = 228;

        when(categoryRepositoryMock.existsById(id))
                .thenReturn(true);
        doNothing().when(categoryRepositoryMock).deleteById(id);

        categoryService.deleteCategoryById(id);
    }

    @Test
    public void deleteCategoryByIncorrectIdTest() {
        int id = 228;

        when(categoryRepositoryMock.existsById(id))
                .thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> categoryService.deleteCategoryById(id));
    }
}
