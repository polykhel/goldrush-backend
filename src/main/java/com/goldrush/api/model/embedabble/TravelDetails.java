package com.goldrush.api.model.embedabble;

import com.goldrush.api.model.Country;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class TravelDetails implements Serializable {

  @ManyToOne
  private Country country;

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
