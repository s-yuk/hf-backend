package com.example.api.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.api.model.entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
  List<Product> findByUserId(String id);
}