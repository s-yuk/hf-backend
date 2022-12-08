package com.example.api.service.Impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.model.Product;
import com.example.api.repo.ProductRepo;
import com.example.api.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

  @Autowired
  ProductRepo _productRepo;

  @Override
  public List<Product> getHaveProduct() {
    return _productRepo.findAll();
  }

  @Override
  public void saveProduct(Product product) {
    _productRepo.save(product);
  }
}
