package com.goldrush.api.service;

import com.goldrush.api.dto.InquiryDto;
import com.goldrush.api.dto.request.InquiryParamsDto;
import com.goldrush.api.dto.response.InquiryStatusDto;
import com.goldrush.api.mapper.InquiryMapper;
import com.goldrush.api.model.Inquiry;
import com.goldrush.api.model.QInquiry;
import com.goldrush.api.model.enums.InquiryStatus;
import com.goldrush.api.repository.InquiryRepository;
import java.util.Arrays;
import java.util.List;

import com.querydsl.core.BooleanBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class InquiryService
    extends BaseService<Inquiry, InquiryDto, InquiryRepository, InquiryMapper> {

  public InquiryService(InquiryRepository repository, InquiryMapper mapper) {
    super(Inquiry.class, repository, mapper);
  }

  public Page<InquiryDto> getInquiries(InquiryParamsDto params, Pageable pageable) {
    QInquiry inquiry = QInquiry.inquiry;

    BooleanBuilder predicate = new BooleanBuilder();

    if (StringUtils.isNotBlank(params.query())) {
      predicate.and(
              inquiry
                      .clientName
                      .containsIgnoreCase(params.query())
                      .or(inquiry.travelDetails.destination.containsIgnoreCase(params.query())));
    }

    if (params.status() != null) {
      predicate.and(inquiry.status.eq(params.status()));
    }

    return repository.findAll(predicate, pageable).map(mapper::toDto);
  }

  public List<InquiryStatusDto> getInquiryStatuses() {
    return Arrays.stream(InquiryStatus.values())
        .map(inquiryStatus -> new InquiryStatusDto(inquiryStatus.getLabel(), inquiryStatus.name()))
        .toList();
  }
}
