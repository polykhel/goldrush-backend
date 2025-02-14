package com.goldrush.api.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Country extends BaseEntity {

  private String code;

  private String name;
}
