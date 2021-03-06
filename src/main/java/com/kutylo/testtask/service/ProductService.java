package com.kutylo.testtask.service;

import com.kutylo.testtask.dto.request.ProductRequest;
import com.kutylo.testtask.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {
    List<ProductResponse> findAllProducts();

    ProductResponse findProductById(int id);

    List<ProductResponse> findProductsByCategory(String category);

    ProductResponse saveProduct(ProductRequest productRequest, String currency);

    ProductResponse updateProductById(ProductRequest productRequest, int id, String currency);

    void deleteProductById(int id);
}
