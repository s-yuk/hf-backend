package com.example.api.model.dto;

import com.example.api.model.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserById {
  private String username;
  private String email;
  private String havePoint;
  private String haveStock;
  private String groupId;
  private Role role;
}
