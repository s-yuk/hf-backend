package com.example.api.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.repo.UserRepo;
import com.example.api.service.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepo userRepo;

  @Override
  public String deleteUserById(String id) {
    userRepo.deleteById(id);
    return id;
  }

}