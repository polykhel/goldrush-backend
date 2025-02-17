package com.goldrush.api.model;

import com.goldrush.api.model.embedabble.TravelDetails;
import com.goldrush.api.model.enums.InquiryStatus;
import com.goldrush.api.model.enums.PackageType;
import jakarta.persistence.*;

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

  @Embedded
  private TravelDetails travelDetails;

  private PackageType packageType;

  private String customPackageOptions;

  @OneToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "inquiry_provider_quotations",
          joinColumns = @JoinColumn(name = "inquiry_id"),
          inverseJoinColumns = @JoinColumn(name = "provider_quotation_id"))
  private List<ProviderQuotation> quotations;

  private String remarks;
}
