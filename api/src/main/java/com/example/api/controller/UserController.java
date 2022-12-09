package com.example.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.model.User;
import com.example.api.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
  @Autowired
  private UserService _userService;

  @GetMapping()
  public ResponseEntity<List<User>> getUsers() {
    List<User> users = _userService.getUsers();
    return ResponseEntity.ok(users);
  }

  @PostMapping("/user/save")
  public ResponseEntity<?> saveUser(@RequestBody User user) {
    _userService.saveUser(user);
    return ResponseEntity.ok().build();
  }

  @PutMapping("user/{id}/")
  public ResponseEntity<?> updateUserPoint(@PathVariable Long id, @RequestBody User user) {
    user.setId(id);
    _userService.updateUserById(user);
    return ResponseEntity.ok().build();
  }
}
