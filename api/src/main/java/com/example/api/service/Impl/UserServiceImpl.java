package com.example.api.service.Impl;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.api.model.dto.HandleErrorDto;
import com.example.api.model.entity.User;
import com.example.api.model.form.UpdateUserForm;
import com.example.api.repo.UserRepo;
import com.example.api.service.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepo userRepo;

  private final PasswordEncoder passwordEncoder;

  @Override
  public HandleErrorDto updateUserById(String id, UpdateUserForm form) {

    HandleErrorDto handleErrorDto = new HandleErrorDto();
    handleErrorDto.setId(id);

    User user = userRepo.findById(id).orElseThrow(() -> new EntityNotFoundException());

    boolean isMatchedPassword = isPasswordValid(form.getPassword(), user.getPassword());

    if (isMatchedPassword) {
      user.setUsername(form.getUsername());
      user.setEmail(form.getEmail());
      user.setPassword(passwordEncoder.encode(form.getNewPassword()));

      handleErrorDto.setMessage("更新しました。");
      userRepo.save(user);
    } else {
      handleErrorDto.setMessage("パスワードが間違っています。");
    }

    return handleErrorDto;
  }

  // パスワードの正誤判定
  public boolean isPasswordValid(String plainPassword, String encodedPassword) {
    return passwordEncoder.matches(plainPassword, encodedPassword);
  }

  @Override
  public HandleErrorDto deleteUserById(String id) {
    HandleErrorDto handleErrorDto = new HandleErrorDto();

    userRepo.deleteById(id);

    handleErrorDto.setId(id);
    handleErrorDto.setMessage("削除しました。");
    return handleErrorDto;
  }

}