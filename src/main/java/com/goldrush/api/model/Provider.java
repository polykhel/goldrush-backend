package com.goldrush.api.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Provider extends BaseEntity {

  private String name;

  private String logo;

  private String email;

  private String trackerLink;
}
