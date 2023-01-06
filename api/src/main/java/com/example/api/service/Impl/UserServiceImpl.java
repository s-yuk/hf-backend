package com.example.api.service.Impl;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.api.config.JwtUtils;
import com.example.api.model.Role;
import com.example.api.model.dto.LoginUser;
import com.example.api.model.entity.User;
import com.example.api.model.form.Email;
import com.example.api.model.form.UpdateLoginUserForm;
import com.example.api.repo.UserRepo;
import com.example.api.service.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j

// ログインユーザー取得
// ログインユーザー更新
// ログインユーザー削除
// ポイント追加
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepo userRepo;
  private final PasswordEncoder passwordEncoder;

  @Override
  public List<User> userList() {
    return userRepo.findAll();
  }

  @Override
  public LoginUser getLoginUser(String token) {
    JwtUtils jwtUtils = new JwtUtils();
    String _id = jwtUtils.decodeJwtToken(token);
    User user = userRepo.findById(_id).orElseThrow(() -> new EntityNotFoundException());
    LoginUser loginUser = new LoginUser();
    loginUser.setUsername(user.getUsername());
    loginUser.setEmail(user.getEmail());
    loginUser.setHavePoint(user.getHavePoint());
    loginUser.setHaveStock(user.getHaveStock());
    loginUser.setGroupId(user.getGroupId());
    loginUser.setRole(user.getRole());
    return loginUser;
  }

  @Override
  public List<User> getGroupUser(String token) {
    JwtUtils jwtUtils = new JwtUtils();
    String _id = jwtUtils.decodeJwtToken(token);
    List<User> user = userRepo.findAllByGroupIdAndRole(_id, Role.CHILD)
        .orElseThrow(() -> new EntityNotFoundException());

    return user;
  }

  @Override
  public void addGroup(String token, Email email) {
    JwtUtils jwtUtils = new JwtUtils();
    String _id = jwtUtils.decodeJwtToken(token);
    User user = userRepo.findByEmail(email.getEmail());
    user.setGroupId(_id);
    userRepo.save(user);
  }

  @Override
  public void updateLoginUser(String token, UpdateLoginUserForm form) {
    JwtUtils jwtUtils = new JwtUtils();
    String _id = jwtUtils.decodeJwtToken(token);
    User user = userRepo.findById(_id).orElseThrow(() -> new EntityNotFoundException());

    boolean isMatchedPassword = isPasswordValid(form.getPassword(), user.getPassword());

    if (isMatchedPassword) {
      user.setUsername(form.getUsername());
      user.setEmail(form.getEmail());
      user.setPassword(passwordEncoder.encode(form.getNewPassword()));

      userRepo.save(user);
    }
  };

  @Override
  public void deleteLoginUser(String token) {
    JwtUtils jwtUtils = new JwtUtils();
    String _id = jwtUtils.decodeJwtToken(token);
    userRepo.deleteById(_id);
  };

  @Override
  public void addChildPoint(String havePoint, String id) {
    User user = userRepo.findById(id).orElseThrow(() -> new EntityNotFoundException());
    user.setHavePoint(havePoint);
    userRepo.save(user);
  }

  // パスワードの正誤判定
  public boolean isPasswordValid(String plainPassword, String encodedPassword) {
    return passwordEncoder.matches(plainPassword, encodedPassword);
  }
}