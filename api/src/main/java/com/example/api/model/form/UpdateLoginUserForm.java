package com.example.api.model.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateLoginUserForm {
  private String username;
  private String email;
  private String password;
  private String newPassword;
}
