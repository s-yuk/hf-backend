package com.example.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.model.Product;
import com.example.api.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {
  private final ProductService productService;
  @GetMapping("/products")
  public ResponseEntity<List<Product>> getUserProducts() {
    List<Product> products = productService.getProducts();
    return ResponseEntity.ok(products);
  }
}
