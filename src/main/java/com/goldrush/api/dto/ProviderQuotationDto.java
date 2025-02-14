package com.goldrush.api.dto;

import com.goldrush.api.model.enums.QuotationStatus;
import java.math.BigDecimal;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProviderQuotationDto extends BaseDto {
  private String providerId;
  private String currencyCode;
  private BigDecimal priceAmount;
  private BigDecimal exchangeRate;
  private BigDecimal phpEquivalentAmount;
  private String emailQuotation;
  private String internalRemarks;
  private QuotationStatus status;
}
