package com.example.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.config.JwtUtils;
import com.example.api.model.entity.Product;
import com.example.api.model.entity.User;
import com.example.api.model.form.ProductForm;
import com.example.api.repo.ProductRepo;
import com.example.api.repo.UserRepo;

import ch.qos.logback.core.status.Status;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static org.springframework.http.HttpHeaders.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/product")
@AllArgsConstructor
@Slf4j
public class ProductController {
  @Autowired
  ProductRepo productRepo;
  @Autowired
  UserRepo userRepo;

  @GetMapping("")
  public ResponseEntity<List<Product>> getProducts(HttpServletRequest request) {
    String token = request.getHeader(AUTHORIZATION);
    JwtUtils jwtUtils = new JwtUtils();
    String id = jwtUtils.decodeJwtToken(token);
    List<Product> products = productRepo.findByUsersId(id);
    return ResponseEntity.ok(products);
  }

  @PostMapping("")
  public ResponseEntity<?> addProduct(HttpServletRequest request, @RequestBody Product form) {
    String token = request.getHeader(AUTHORIZATION);
    JwtUtils jwtUtils = new JwtUtils();
    String id = jwtUtils.decodeJwtToken(token);

    productRepo.save(form);

    return new ResponseEntity<>(HttpStatus.OK);
  }
}