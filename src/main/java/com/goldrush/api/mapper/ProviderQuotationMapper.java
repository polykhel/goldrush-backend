package com.goldrush.api.mapper;

import com.goldrush.api.model.ProviderQuotation;
import com.goldrush.api.dto.ProviderQuotationDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProviderQuotationMapper extends BaseMapper<ProviderQuotation, ProviderQuotationDto> {

  @Mapping(target = "provider.id", source = "providerId")
  ProviderQuotation toEntity(ProviderQuotationDto providerQuotation);

  @InheritInverseConfiguration
  ProviderQuotationDto toDto(ProviderQuotation providerQuotation);
}
