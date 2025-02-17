package com.goldrush.api.dto.request;

import com.goldrush.api.model.enums.InquiryStatus;

public record InquiryParamsDto(String query, InquiryStatus status) {}
