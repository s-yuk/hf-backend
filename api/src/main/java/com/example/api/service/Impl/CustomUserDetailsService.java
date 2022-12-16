package com.example.api.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.api.model.CustomUserDetails;
import com.example.api.model.entity.UserEntity;
import com.example.api.repo.UserRepo;

public class CustomUserDetailsService implements UserDetailsService {
  @Autowired
  UserRepo userRepo;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    UserEntity userEntity = userRepo.findByEmail(email);
    if (userEntity == null) {
      throw new UsernameNotFoundException("No user found with the given email");
    }
    return new CustomUserDetails(userEntity);
  }

}
