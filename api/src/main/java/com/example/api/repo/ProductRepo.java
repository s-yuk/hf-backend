package com.example.api.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api.model.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {}
