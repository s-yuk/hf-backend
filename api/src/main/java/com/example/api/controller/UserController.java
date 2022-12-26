package com.example.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.model.dto.HandleErrorDto;
import com.example.api.model.dto.UserById;
import com.example.api.model.form.UpdateUserForm;
import com.example.api.service.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController @RequestMapping("/api/user") @AllArgsConstructor @Slf4j

// 一件検索
// 更新
// 削除
public class UserController {
  @Autowired
  UserService userService;

  @GetMapping("/{id}")
  public ResponseEntity<UserById> getUserById(@PathVariable String id) {
    UserById user = userService.getUserById(id);
    return ResponseEntity.ok(user);
  }

  @PutMapping("/{id}")
  public HandleErrorDto updateUser(@PathVariable String id, @RequestBody UpdateUserForm form) {

    HandleErrorDto handleErrorDto = userService.updateUserById(id, form);
    return handleErrorDto;
  }

  @DeleteMapping("/{id}")
  public HandleErrorDto deleteUser(@PathVariable String id) {

    HandleErrorDto handleErrorDto =  userService.deleteUserById(id);
    return handleErrorDto;
  }
}
