package com.example.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
}
