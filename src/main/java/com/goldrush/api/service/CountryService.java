package com.goldrush.api.service;

import com.goldrush.api.dto.CountryDto;
import com.goldrush.api.mapper.CountryMapper;
import com.goldrush.api.model.Country;
import com.goldrush.api.repository.CountryRepository;
import java.util.Comparator;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CountryService
    extends BaseService<Country, CountryDto, CountryRepository, CountryMapper> {

  public CountryService(CountryRepository countryRepository, CountryMapper countryMapper) {
    super(Country.class, countryRepository, countryMapper);
  }

  @Override
  public List<CountryDto> getAll() {
    return repository.findAll().stream()
        .map(mapper::toDto)
        .sorted(Comparator.comparing(CountryDto::getName))
        .toList();
  }
}
