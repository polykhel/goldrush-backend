package com.goldrush.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CountryDto extends BaseDto {
  private String code;
  private String name;
}
