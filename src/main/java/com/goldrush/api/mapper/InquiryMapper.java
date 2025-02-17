package com.goldrush.api.mapper;

import com.goldrush.api.model.Inquiry;
import com.goldrush.api.dto.InquiryDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ProviderQuotationMapper.class})
public interface InquiryMapper extends BaseMapper<Inquiry, InquiryDto> {

    @Mapping(target = "travelDetails.countryId", source = "travelDetails.country.id")
    InquiryDto toDto(Inquiry inquiry);

    @InheritInverseConfiguration
    Inquiry toEntity(InquiryDto inquiryDto);
}
