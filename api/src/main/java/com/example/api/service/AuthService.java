package com.example.api.service;

import com.example.api.model.dto.LoginDto;
import com.example.api.model.form.LoginForm;
import com.example.api.model.form.SignUpForm;

public interface AuthService {
  String signUp(SignUpForm form);

  LoginDto login(LoginForm form);
}
