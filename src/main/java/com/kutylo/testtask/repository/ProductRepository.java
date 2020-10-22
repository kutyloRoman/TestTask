package com.kutylo.testtask.repository;

import com.kutylo.testtask.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
