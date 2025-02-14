package com.goldrush.api.mapper;

import com.goldrush.api.model.Inquiry;
import com.goldrush.api.dto.InquiryDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {ProviderQuotationMapper.class})
public interface InquiryMapper extends BaseMapper<Inquiry, InquiryDto> {
}
