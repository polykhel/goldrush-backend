package com.goldrush.api.web;

import com.goldrush.api.dto.CountryDto;
import com.goldrush.api.service.CountryService;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/country")
public class CountryResource {

  private final CountryService countryService;

  public CountryResource(CountryService countryService) {
    this.countryService = countryService;
  }

  @GetMapping
  public List<CountryDto> getCountries() {
    return countryService.getAll();
  }
}
