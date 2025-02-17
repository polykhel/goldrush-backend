package com.goldrush.api.dto;

import java.time.Instant;
import java.util.UUID;
import lombok.Data;

@Data
public class BaseDto {
  private UUID id;
  private Instant createdAt;
  private Instant updatedAt;
  private String createdBy;
  private String updatedBy;
  private boolean deleted;
  private Instant deletedAt;
}
