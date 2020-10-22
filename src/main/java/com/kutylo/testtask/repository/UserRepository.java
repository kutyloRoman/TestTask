package com.kutylo.testtask.repository;

import com.kutylo.testtask.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
