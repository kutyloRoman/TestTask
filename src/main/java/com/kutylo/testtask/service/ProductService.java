package com.kutylo.testtask.service;

import com.kutylo.testtask.dto.request.ProductRequest;
import com.kutylo.testtask.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {
    List<ProductResponse> findAllProducts();

    ProductResponse findProductById(int id);

    ProductResponse saveProduct(ProductRequest productRequest);

    ProductResponse updateProduct(ProductRequest productRequest, int id);

    void deleteProduct(int id);
}
