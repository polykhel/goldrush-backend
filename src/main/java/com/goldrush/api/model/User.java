package com.goldrush.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "app_user", indexes = @Index(columnList = "googleId"))
@Getter
@Setter
public class User extends BaseEntity {
  @NotBlank
  @Email
  @Column(unique = true)
  private String email;
  @NotBlank
  private String name;
  private String googleId;
  private String profileImage;
  private String role;
  @NotBlank
  private String password;
}
