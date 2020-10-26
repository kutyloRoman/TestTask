package com.kutylo.testtask.controller;


import com.kutylo.testtask.dto.request.ProductRequest;
import com.kutylo.testtask.dto.response.ProductResponse;
import com.kutylo.testtask.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("products/")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @ApiOperation(value = "Get all products")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Products found", response = ProductResponse.class)
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<ProductResponse> findAllProducts() {
        return productService.findAllProducts();
    }

    @ApiOperation(value = "Get all products by category")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Products found", response = ProductResponse.class)
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("category/{category}")
    public List<ProductResponse> findProductsByCategory(@PathVariable String category) {
        return productService.findProductsByCategory(category);
    }

    @ApiOperation(value = "Get product by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Product found", response = ProductResponse.class)
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ProductResponse findProductById(@PathVariable int id) {
        return productService.findProductById(id);
    }

    @ApiOperation(value = "Create new product")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Product created", response = ProductResponse.class)
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ProductResponse saveNewProduct(@RequestBody @Validated ProductRequest productRequest, String currency) {
        return productService.saveProduct(productRequest, currency);
    }

    @ApiOperation(value = "Update product by id")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Product updated", response = ProductResponse.class),
            @ApiResponse(code = 404, message = "Product not found")
    })
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public ProductResponse updateProductById(@RequestBody @Validated ProductRequest productRequest, @PathVariable int id, String currency) {
        return productService.updateProductById(productRequest, id, currency);
    }

    @ApiOperation(value = "Delete product by id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Product deleted", response = ProductResponse.class),
            @ApiResponse(code = 404, message = "Product not found")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable int id) {
        productService.deleteProductById(id);
    }
}
