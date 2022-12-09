package com.example.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.model.Product;
import com.example.api.service.ProductService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/api/product")
@AllArgsConstructor
@Slf4j
public class ProductController {
  @Autowired
  ProductService _productService;

  @GetMapping
  public ResponseEntity<List<Product>> getUserProducts() {
    List<Product> products = _productService.getHaveProduct();
    return ResponseEntity.ok(products);
  }

  @PostMapping("/save")
  public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
    log.info("{}", product);
    _productService.saveProduct(product);
    return ResponseEntity.ok(product);
  }
}