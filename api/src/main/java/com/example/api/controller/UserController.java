package com.example.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.model.dto.HandleErrorDto;
import com.example.api.model.form.UpdateUserForm;
import com.example.api.service.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController @RequestMapping("/api/user") @AllArgsConstructor @Slf4j
public class UserController {
  @Autowired
  UserService userService;

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
