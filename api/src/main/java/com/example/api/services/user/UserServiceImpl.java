package com.example.api.services.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.models.User;
import com.example.api.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private UserRepository _userRepository;

  @Override
  public List<User> getUserName() {
    return _userRepository.findAll();
  };
}
