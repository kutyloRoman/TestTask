package com.kutylo.testtask.mapper;

import com.kutylo.testtask.dto.request.ProductRequest;
import com.kutylo.testtask.dto.response.ProductResponse;
import com.kutylo.testtask.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product requestToModel(ProductRequest productResponse);

    ProductResponse modelToResponse(Product product);

    Product requestToEntity(ProductRequest dto, @MappingTarget Product product);
}
