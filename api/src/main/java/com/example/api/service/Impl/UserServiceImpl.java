package com.example.api.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.api.model.User;
import com.example.api.repo.UserRepo;
import com.example.api.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
  @Autowired
  private UserRepo _userRepo;

  @Override
  public List<User> getUsers() {
    return _userRepo.findAll();
  }

  @Override
  public User saveUser(User user) {
    return _userRepo.save(user);
  };

  @Override
  public User updateUserById(User user) {
    return _userRepo.save(user);
  };
}
