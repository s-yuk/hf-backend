package com.example.api.service.Impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.api.model.Product;
import com.example.api.repo.ProductRepo;
import com.example.api.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service @RequiredArgsConstructor @Transactional
public class ProcuctServiceImpl implements ProductService {
  private final ProductRepo productRepo;

  @Override
  public List<Product> getProducts() {
    return productRepo.findAll();
  }

  @Override
  public List<Product> putProducts() {
    return productRepo.delete();
  }

}
