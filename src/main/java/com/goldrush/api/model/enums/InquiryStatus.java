package com.goldrush.api.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum InquiryStatus {
  NEW("New"),
  PENDING("Pending"),
  QUOTED("Quoted"),
  CONVERTED("Converted"),
  CLOSED("Closed");

  private final String label;
}
