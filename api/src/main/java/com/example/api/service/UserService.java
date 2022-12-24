package com.example.api.service;

import com.example.api.model.dto.HandleErrorDto;
import com.example.api.model.form.UpdateUserForm;

public interface UserService {
  HandleErrorDto updateUserById(String id, UpdateUserForm form);

  HandleErrorDto deleteUserById(String id);
}