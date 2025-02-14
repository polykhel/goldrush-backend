package com.goldrush.api.mapper;

import com.goldrush.api.model.Country;
import com.goldrush.api.dto.CountryDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryMapper extends BaseMapper<Country, CountryDto> {
}
