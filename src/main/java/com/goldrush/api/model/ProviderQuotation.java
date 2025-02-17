package com.goldrush.api.model;

import com.goldrush.api.model.enums.QuotationStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.Instant;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProviderQuotation extends BaseEntity {

  @ManyToOne(optional = false)
  private Provider provider;

  @Column(length = 3)
  private String currencyCode;

  @Column(precision = 10, scale = 2)
  private BigDecimal priceAmount;

  @Column(precision = 10, scale = 2)
  private BigDecimal exchangeRate;

  @Column(precision = 10, scale = 2)
  private BigDecimal phpEquivalentAmount;

  private Instant exchangeRateLastUpdated;

  private String emailQuotation;

  private String internalRemarks;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private QuotationStatus status;

  @Column(columnDefinition = "boolean default false")
  private boolean sent;
}
