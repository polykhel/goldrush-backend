package com.goldrush.api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProviderDto extends BaseDto {
  private String name;
  private String logo;
  private String email;
  private String trackerLink;
}
