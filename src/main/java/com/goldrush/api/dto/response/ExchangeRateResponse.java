package com.goldrush.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.Instant;

public record ExchangeRateResponse(
    @JsonProperty("conversion_rate") BigDecimal conversionRate,
    @JsonProperty("time_last_update_unix") Instant timeLastUpdate,
    @JsonProperty("time_next_update_unix") Instant timeNextUpdate) {}
