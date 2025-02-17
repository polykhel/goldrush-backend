package com.goldrush.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserDto extends BaseDto {
  private String name;
  private String username;
  private String email;
  private String profileImage;
  private String role;
}
