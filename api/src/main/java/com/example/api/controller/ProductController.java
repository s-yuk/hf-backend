package com.example.api.controller;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.config.JwtUtils;
import com.example.api.model.dto.ProductDto;
import com.example.api.model.entity.Product;
import com.example.api.model.entity.User;
import com.example.api.model.form.ProductForm;
import com.example.api.model.form.UpdateProductForm;
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

  // 500エラー
  // 多分関数の型をdtoフォルダに定義してそれに値をセットしてけばできる気がする
  @GetMapping("")
  public ResponseEntity<List<Product>> getProducts(HttpServletRequest request) {
    String token = request.getHeader(AUTHORIZATION);
    JwtUtils jwtUtils = new JwtUtils();
    String id = jwtUtils.decodeJwtToken(token);

    List<Product> products = productRepo.findAllByUserId(id).forEach({});
    return ResponseEntity.ok(products);
  }

  @PostMapping("")
  public ResponseEntity<?> addProduct(HttpServletRequest request, @RequestBody ProductForm form) {
    String token = request.getHeader(AUTHORIZATION);
    JwtUtils jwtUtils = new JwtUtils();
    String id = jwtUtils.decodeJwtToken(token);
    User user = userRepo.findById(id).orElseThrow(() -> new EntityNotFoundException());

    Product product = new Product();
    product.setName(form.getName());
    product.setUsePoint(form.getUsePoint());
    product.setUser(user);

    productRepo.save(product);

    return new ResponseEntity<>(HttpStatus.OK);
  }

  // 商品名と必要ポイントの更新
  @PutMapping("/{id}")
  public ResponseEntity<?> updateProduct(@RequestBody UpdateProductForm form, @PathVariable Long id) {
    Product product = productRepo.findById(id).orElseThrow(() -> new EntityNotFoundException());
    product.setName(form.getName());
    product.setUsePoint(form.getUsePoint());
    productRepo.save(product);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  // 商品削除
  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteProductEntity(@PathVariable Long id) {
    productRepo.deleteById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}