package com.example.api.service;

import com.example.api.model.dto.SignUpDto;
import com.example.api.model.form.SignUpForm;

public interface AuthService {
  SignUpDto signUp(SignUpForm form);
}
