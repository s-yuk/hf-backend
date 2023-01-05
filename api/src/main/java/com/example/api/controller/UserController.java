package com.example.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.service.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController @RequestMapping("/api/user") @AllArgsConstructor @Slf4j
public class UserController {
  @Autowired
  UserService userService;

  @DeleteMapping("/{id}")
  public String deleteUser(@PathVariable String id) {

    String _id =  userService.deleteUserById(id);
    return _id + "のユーザーを削除しました。";
  }
}
