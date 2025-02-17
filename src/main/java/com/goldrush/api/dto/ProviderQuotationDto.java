package com.goldrush.api.dto;

import com.goldrush.api.model.enums.QuotationStatus;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProviderQuotationDto extends BaseDto {
  private UUID providerId;
  private String currencyCode;
  private BigDecimal priceAmount;
  private BigDecimal exchangeRate;
  private BigDecimal phpEquivalentAmount;
  private Instant exchangeRateLastUpdated;
  private String emailQuotation;
  private String internalRemarks;
  private QuotationStatus status;
  private boolean sent;
}
