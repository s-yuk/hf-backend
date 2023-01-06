package com.example.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.model.dto.LoginUser;
import com.example.api.model.entity.User;
import com.example.api.model.form.AddPointForm;
import com.example.api.model.form.UpdateLoginUserForm;
import com.example.api.service.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
@Slf4j

// ログインユーザー取得
// ログインユーザー更新
// ログインユーザー削除
// ポイント追加
public class UserController {
  @Autowired
  UserService userService;

  @GetMapping("/all")
  public ResponseEntity<List<User>> userGet() {
    List<User> users = userService.userList();
    return ResponseEntity.ok(users);
  }

  @GetMapping()
  public ResponseEntity<LoginUser> getLoginUser(@CookieValue("token") String token) {
    log.info("token:", token);
    LoginUser user = userService.getLoginUser(token);
    return ResponseEntity.ok(user);
  }

  @PutMapping()
  public ResponseEntity<?> updateLoginUser(@CookieValue("token") String token, @RequestBody UpdateLoginUserForm form) {
    userService.updateLoginUser(token, form);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping()
  public ResponseEntity<?> deleteLoginUser(@CookieValue("token") String token) {
    userService.deleteLoginUser(token);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PutMapping("/{id}/point")
  public ResponseEntity<?> addChildPoint(@RequestBody AddPointForm form, @PathVariable String id) {
    userService.addChildPoint(form.getHavePoint(), id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
