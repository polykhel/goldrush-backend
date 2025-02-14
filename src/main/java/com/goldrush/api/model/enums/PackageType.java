package com.goldrush.api.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PackageType {
  ALL_INCLUSIVE("All-Inclusive Package"),
  CUSTOM("Custom Package");

  private final String name;
}
