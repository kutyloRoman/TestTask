package com.kutylo.testtask.service.impl;

import com.kutylo.testtask.dto.request.ProductRequest;
import com.kutylo.testtask.dto.response.ProductResponse;
import com.kutylo.testtask.feign.fixer.FixerClient;
import com.kutylo.testtask.feign.fixer.FixerResponse;
import com.kutylo.testtask.handler.exception.FixerException;
import com.kutylo.testtask.mapper.ProductMapper;
import com.kutylo.testtask.model.Product;
import com.kutylo.testtask.repository.CategoryRepository;
import com.kutylo.testtask.repository.ProductRepository;
import com.kutylo.testtask.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
//@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;
    private final FixerClient fixerClient;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper, CategoryRepository categoryRepository, FixerClient fixerClient) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.categoryRepository = categoryRepository;
        this.fixerClient = fixerClient;
    }

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
        log.info("Getting product by category name:" + category);
        List<Product> products = productRepository.findProductByCategoryName(category);

        return products.stream()
                .map(productMapper::modelToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse saveProduct(ProductRequest productRequest, String currency) {
        log.info("Saving product = " + productRequest);
        checkValidCategory(productRequest.getCategory().getId());

        Product product = productMapper.requestToModel(productRequest);
        product.setPrice(product.getPrice().divide(getRateOfGivenCurrency(currency), 3, RoundingMode.HALF_UP));
        productRepository.save(product);

        return productMapper.modelToResponse(product);
    }

    @Override
    public ProductResponse updateProductById(ProductRequest productRequest, int id, String currency) {
        log.info("Update product with id: " + id);
        checkValidProduct(id);
        checkValidCategory(productRequest.getCategory().getId());

        Product product = productMapper.requestToModel(productRequest);
        product.setPrice(product.getPrice().divide(getRateOfGivenCurrency(currency), 3, RoundingMode.HALF_UP));
        product.setId(id);
        productRepository.save(product);

        return productMapper.modelToResponse(product);
    }

    @Override
    public void deleteProductById(int id) {
        log.info("Deleting product with id = " + id);
        checkValidProduct(id);
        productRepository.deleteById(id);
    }

    private void checkValidCategory(int id) {
        log.info("Checking category with id = " + id);
        if (!categoryRepository.existsById(id)) {
            log.error("Category with id = " + id + " not found");
            throw new EntityNotFoundException("Category with id = " + id + " not found");
        }
    }

    private void checkValidProduct(int id) {
        log.info("Checking product with id = " + id);
        if (!productRepository.existsById(id)) {
            log.error("Product with id = " + id + " not found");
            throw new EntityNotFoundException("Product with id = " + id + " not found");
        }
    }

    private BigDecimal getRateOfGivenCurrency(String currency) {
        log.info("Getting rate of " + currency + " currency");
        FixerResponse fixerResponse = fixerClient.getRecentExchangeRateData();
        if (fixerResponse.isSuccess()) {
            Map<String, Double> rates = fixerResponse.getRates();
            if (rates.containsKey(currency.toUpperCase())) {
                return BigDecimal.valueOf(rates.get(currency));
            } else {
                log.error(currency + " currency not found");
                throw new IllegalArgumentException(currency + " currency not found");
            }
        }
        log.error("Fixer.io return incorrect response");
        throw new FixerException("Fixer error");
    }
}
