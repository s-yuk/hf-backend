package com.example.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserAuthentification implements Serializable {
  private String userId;
  private String password;
}
