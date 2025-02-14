package com.goldrush.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "app_user", indexes = @Index(columnList = "googleId"))
@Getter
@Setter
public class User extends BaseEntity {

  private String email;

  private String name;

  private String googleId;

  private String profileImage;

  private String role;
}
