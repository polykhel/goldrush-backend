package com.goldrush.api.dto;

import java.time.LocalDate;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TravelDetailsDto {
  private UUID countryId;

  private String destination;

  private int days;

  private int nights;

  private LocalDate startDate;

  private LocalDate endDate;

  private String preferredHotel;

  private int adults;

  private int children;

  private String childAges;
}
