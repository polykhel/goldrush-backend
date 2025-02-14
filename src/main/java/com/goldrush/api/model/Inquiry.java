package com.goldrush.api.model;

import com.goldrush.api.model.embedabble.TravelDetails;
import com.goldrush.api.model.enums.InquiryStatus;
import com.goldrush.api.model.enums.PackageType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Inquiry extends BaseEntity {

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private InquiryStatus status;

  private LocalDate date;

  private String clientName;

  private String source;

  @Embedded private TravelDetails travelDetails;

  private PackageType packageType;

  private String customPackageOptions;

  @OneToMany
  private List<ProviderQuotation> quotations;

  private String remarks;
}
