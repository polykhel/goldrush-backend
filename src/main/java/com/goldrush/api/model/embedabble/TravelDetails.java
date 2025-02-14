package com.goldrush.api.model.embedabble;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class TravelDetails implements Serializable {

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
