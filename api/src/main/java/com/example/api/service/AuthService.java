package com.example.api.service;

import com.example.api.model.form.SignUpForm;

public interface AuthService {
  String signUp(SignUpForm form);
}
