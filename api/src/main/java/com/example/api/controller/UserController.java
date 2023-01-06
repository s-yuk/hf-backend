package com.example.api.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.config.JwtUtils;
import com.example.api.model.dto.LoginUser;
import com.example.api.model.entity.User;
import com.example.api.model.form.AddPointForm;
import com.example.api.model.form.Email;
import com.example.api.model.form.StockForm;
import com.example.api.model.form.UpdateLoginUserForm;
import com.example.api.repo.UserRepo;
import com.example.api.service.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static org.springframework.http.HttpHeaders.*;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
@Slf4j
public class UserController {
  @Autowired
  UserService userService;

  @Autowired
  UserRepo userRepo;

  // 全ユーザー取得
  @GetMapping("/all")
  public ResponseEntity<List<User>> userGet() {
    List<User> users = userService.userList();
    return ResponseEntity.ok(users);
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(@PathVariable String id) {
    User user = userRepo.findById(id).orElseThrow(() -> new EntityNotFoundException());
    return ResponseEntity.ok(user);
  }

  // ログインユーザー取得
  @GetMapping()
  public ResponseEntity<LoginUser> getLoginUser(HttpServletRequest request) {
    String token = request.getHeader(AUTHORIZATION);
    LoginUser user = userService.getLoginUser(token);
    return ResponseEntity.ok(user);
  }

  // 子ども一覧取得
  @GetMapping("/group")
  public ResponseEntity<List<User>> getGroupUser(HttpServletRequest request) {
    String token = request.getHeader(AUTHORIZATION);
    List<User> children = userService.getGroupUser(token);
    return ResponseEntity.ok(children);
  }

  @PutMapping("/group")
  public ResponseEntity<?> addGroup(HttpServletRequest request, @RequestBody Email email) {
    String token = request.getHeader(AUTHORIZATION);
    userService.addGroup(token, email);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  // ログインユーザー更新
  @PutMapping()
  public ResponseEntity<?> updateLoginUser(HttpServletRequest request, @RequestBody UpdateLoginUserForm form) {
    String token = request.getHeader(AUTHORIZATION);
    userService.updateLoginUser(token, form);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  // ログインユーザー削除
  @DeleteMapping()
  public ResponseEntity<?> deleteLoginUser(HttpServletRequest request) {
    String token = request.getHeader(AUTHORIZATION);
    userService.deleteLoginUser(token);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  // ポイント追加
  @PutMapping("/{id}/point")
  public ResponseEntity<?> addChildPoint(@RequestBody AddPointForm form, @PathVariable String id) {
    userService.addChildPoint(form.getHavePoint(), id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  // 株購入
  @PutMapping("/stock")
  public ResponseEntity<?> addStock(HttpServletRequest request, @RequestBody StockForm form) {
    String token = request.getHeader(AUTHORIZATION);
    JwtUtils jwtUtils = new JwtUtils();
    String id = jwtUtils.decodeJwtToken(token);
    log.info("test: {}", id);
    User user = userRepo.findById(id).orElseThrow(() -> new EntityNotFoundException());
    user.setHavePoint(form.getHavePoint());
    user.setHaveStock(form.getHaveStock());
    userRepo.save(user);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
