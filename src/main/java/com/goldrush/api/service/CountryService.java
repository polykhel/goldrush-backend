package com.goldrush.api.service;

import com.goldrush.api.dto.CountryDto;
import com.goldrush.api.mapper.CountryMapper;
import com.goldrush.api.model.Country;
import com.goldrush.api.repository.CountryRepository;
import org.springframework.stereotype.Service;

@Service
public class CountryService
    extends BaseService<Country, CountryDto, CountryRepository, CountryMapper> {

  public CountryService(CountryRepository countryRepository, CountryMapper countryMapper) {
    super(Country.class, countryRepository, countryMapper);
  }
}
