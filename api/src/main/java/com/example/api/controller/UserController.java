package com.example.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.model.User;
import com.example.api.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
  @Autowired
  private UserService _userService;

  @GetMapping()
  public ResponseEntity<List<User>> getUsers() {
    List<User> users = _userService.getUsers();
    return ResponseEntity.ok(users);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Optional<User>> getUserById(@PathVariable Long id) {
    Optional<User> user = _userService.getUserById(id);
    return ResponseEntity.ok(user);
  }

  @PostMapping("/save")
  public ResponseEntity<User> saveUser(@RequestBody User user) {
    _userService.saveUser(user);
    log.info("{}", user);
    return ResponseEntity.ok(user);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<User> updateUserPoint(@PathVariable Long id, @RequestBody User user) {
    user.setId(id);
    log.info("user: {}", user);
    _userService.updateUserById(user);
    return ResponseEntity.ok(user);
  }
}
