package com.example.api.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.api.repo.UserRepo;

public class CustomUserDetailsService implements UserDetailsService {
  @Autowired
  UserRepo userRepo;
  private final static String USER_NOT_FOUND_MSG = "user with email %s not found";

  @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      return userRepo.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }
}
