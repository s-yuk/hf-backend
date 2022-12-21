package com.example.api.service;

import com.example.api.model.form.RegistrationForm;

public interface AuthService {
  String signUp(RegistrationForm form);
}
