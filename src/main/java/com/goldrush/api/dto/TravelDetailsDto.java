package com.goldrush.api.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TravelDetailsDto {
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
