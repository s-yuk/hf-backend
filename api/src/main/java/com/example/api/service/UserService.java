package com.example.api.service;

import com.example.api.model.dto.HandleErrorDto;
import com.example.api.model.dto.UserById;
import com.example.api.model.form.UpdateUserForm;

public interface UserService {
  UserById getUserById(String id);

  HandleErrorDto updateUserById(String id, UpdateUserForm form);

  HandleErrorDto deleteUserById(String id);
}