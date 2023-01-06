package com.example.api.service;

import java.util.List;

import com.example.api.model.dto.LoginUser;
import com.example.api.model.entity.User;
import com.example.api.model.form.UpdateLoginUserForm;

// ログインユーザー取得
// ログインユーザー更新
// ログインユーザー削除
// ポイント追加
public interface UserService {
  LoginUser getLoginUser(String token);

  void updateLoginUser(String token, UpdateLoginUserForm form);

  void deleteLoginUser(String token);

  void addChildPoint(String havePoint, String id);

  List<User> userList();
}