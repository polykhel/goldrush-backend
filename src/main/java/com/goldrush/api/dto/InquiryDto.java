package com.goldrush.api.dto;

import com.goldrush.api.model.enums.InquiryStatus;
import com.goldrush.api.model.enums.PackageType;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class InquiryDto extends BaseDto {
  private InquiryStatus status;
  private LocalDate date;
  private String clientName;
  private String source;
  private TravelDetailsDto travelDetails;
  private PackageType packageType;
  private String customPackageOptions;
  private List<ProviderQuotationDto> quotations;
  private String remarks;
}
