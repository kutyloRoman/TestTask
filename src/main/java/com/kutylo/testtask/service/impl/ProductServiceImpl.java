package com.kutylo.testtask.service.impl;

import com.kutylo.testtask.dto.request.ProductRequest;
import com.kutylo.testtask.dto.response.ProductResponse;
import com.kutylo.testtask.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    @Override
    public List<ProductResponse> findAllProducts() {
        return null;
    }

    @Override
    public ProductResponse findProductById(int id) {
        return null;
    }

    @Override
    public ProductResponse saveProduct(ProductRequest productRequest) {
        return null;
    }

    @Override
    public ProductResponse updateProduct(ProductRequest productRequest, int id) {
        return null;
    }

    @Override
    public void deleteProduct(int id) {

    }
}
