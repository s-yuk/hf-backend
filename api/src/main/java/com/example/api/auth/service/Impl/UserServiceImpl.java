package com.example.api.auth.service.Impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.api.auth.model.Role;
import com.example.api.auth.model.User;
import com.example.api.auth.repo.RoleRepo;
import com.example.api.auth.repo.UserRepo;
import com.example.api.auth.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
  private final UserRepo userRepo;
  private final RoleRepo roleRepo;
  private final PasswordEncoder passwordEncoder;
  // private final static String USER_NOT_FOUND_MSG = "user with email %s not found";

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    // if (userRepo.findByEmail(email).isEmpty()) {
    //   throw new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email));
    // }
    return null;
  }

  @Override
  public Role saveRole(Role role) {
    log.info("saving new role {} to the database", role.getName());
    return roleRepo.save(role);
  }

  public void signUpUser(User user) {
    boolean userExists = userRepo.findByEmail(user.getEmail()).isPresent();
    if (userExists) {
      throw new IllegalStateException("email already taken");
    }
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepo.save(user);
  }
}
