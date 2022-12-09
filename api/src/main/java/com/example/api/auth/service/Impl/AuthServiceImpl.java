package com.example.api.auth.service.Impl;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.api.auth.service.AuthService;
import com.example.api.model.User;
import com.example.api.repo.UserRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AuthServiceImpl implements AuthService, UserDetailsService {
  private final PasswordEncoder passwordEncoder;
  @Autowired
  private UserRepo _userRepo;

  public void signUpUser(User user) {

    boolean userExists = _userRepo.findByEmail(user.getEmail()).isPresent();
    if (userExists) {
      throw new IllegalStateException("The email address you entered is already in use");
    }
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    _userRepo.save(user);
  }

  @Override
  public User loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = _userRepo.findUserByEmail(email);
    if (user == null) {
      log.error("email not found in database: {}", email);
      throw new UsernameNotFoundException("email not found in database");
    } else {
      log.info("user found in database: {}", email);
    }
    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
    user.getRoles().forEach(role -> {
      authorities.add(new SimpleGrantedAuthority(role.getName()));
    });
    return user;
  }
}
