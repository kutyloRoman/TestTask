package com.kutylo.testtask.mapper;

import com.kutylo.testtask.dto.request.CategoryRequest;
import com.kutylo.testtask.dto.response.CategoryResponse;
import com.kutylo.testtask.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category requestToModel(CategoryRequest categoryRequest);

    CategoryResponse modelToResponse(Category category);

    Category requestToEntity(CategoryRequest dto, @MappingTarget Category category);

    Category responseToModel(CategoryResponse categoryResponse);
}
