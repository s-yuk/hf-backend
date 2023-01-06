package com.example.api.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChildDto {
  private String id;
  private String username;
  private String email;
  private String havePoint;
  private String haveStock;
  private String groupId;
}
