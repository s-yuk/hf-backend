package com.example.api.model.form;

import lombok.Data;

@Data
public class RegistrationForm {
  private String name;
  private String email;
  private String password;

  RegistrationForm(String name, String email, String password) {
    this.name = name;
    this.email = email;
    this.password = password;
  }

  RegistrationForm() {}
}
