package com.kutylo.testtask.service.impl;

import com.kutylo.testtask.dto.request.ProductRequest;
import com.kutylo.testtask.dto.response.ProductResponse;
import com.kutylo.testtask.mapper.ProductMapper;
import com.kutylo.testtask.model.Product;
import com.kutylo.testtask.repository.ProductRepository;
import com.kutylo.testtask.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<ProductResponse> findAllProducts() {
        log.info("Getting list of all products");

        return productRepository.findAll().stream()
                .map(productMapper::modelToResponse)
                .collect(Collectors.toList());

    }

    @Override
    public ProductResponse findProductById(int id) {
        log.info("Getting product by id:" + id);

        Product product = productRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Not found product with id:" + id);
                    return new EntityNotFoundException("Not found product with id:" + id);
                });

        return productMapper.modelToResponse(product);
    }

    @Override
    public List<ProductResponse> findProductsByCategory(String category) {
        List<Product> products = productRepository.findProductByCategoryName(category);

        return products.stream()
                .map(productMapper::modelToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse saveProduct(ProductRequest productRequest) {
        log.info("Saving product = " + productRequest);

        Product product = productMapper.requestToModel(productRequest);
        productRepository.save(product);

        return productMapper.modelToResponse(product);
    }

    @Override
    public ProductResponse updateProductById(ProductRequest productRequest, int id) {
        log.info("Update product with id: " + id);

        Product product = productMapper.requestToModel(productRequest);
        product.setId(id);
        productRepository.save(product);

        return productMapper.modelToResponse(product);
    }

    @Override
    public void deleteProductById(int id) {
        log.info("Deleting product with id = " + id);

        productRepository.deleteById(id);
    }
}
