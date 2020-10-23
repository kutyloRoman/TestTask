package com.kutylo.testtask.repository;

import com.kutylo.testtask.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findProductByCategoryName(String name);
}
