package com.example.api.auth.service.Impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.api.auth.model.Role;
import com.example.api.auth.model.User;
import com.example.api.auth.repo.RoleRepo;
import com.example.api.auth.repo.UserRepo;
import com.example.api.auth.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static org.springframework.http.MediaType.*;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
  private final UserRepo userRepo;
  private final RoleRepo roleRepo;
  private final PasswordEncoder passwordEncoder;
  private final static String USER_NOT_FOUND_MSG = "user with email %s not found";

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepo.findByUsername(username);
    if (user == null) {
      log.error("user not found in database: {}", username);
      throw new UsernameNotFoundException("user not found in database");
    } else {
      log.info("user found in database: {}", username);
    }
    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
    user.getRoles().forEach(role -> {
      authorities.add(new SimpleGrantedAuthority(role.getName()));
    });
    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
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


  // @Override
  // public void addRoleToUser(String username, Long roleId) {
  //   log.info("adding role {} to user {}", roleId, username);
  //   User user = userRepo.findByUsername(username);
  //   Optional<Role> role = roleRepo.findById(roleId);
  //   user.getRoles().add(role);
  // }


  // @Override
  // public User saveUser(User user) {
  //   log.info("saving new user {} to the database", user.getName());
  //   user.setPassword(passwordEncoder.encode(user.getPassword()));
  //   return userRepo.save(user);
  // }



  // @Override
  // public User getUser(String username) {
  //   log.info("fetching user {}", username);
  //   return userRepo.findByUsername(username);
  // }

  // @Override
  // public List<User> getUsers() {
  //   log.info("fetching all users");
  //   return userRepo.findAll();
  // }
}
