package com.kutylo.testtask.repository;

import com.kutylo.testtask.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
