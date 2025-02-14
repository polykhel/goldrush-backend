package com.goldrush.api.service;

import com.goldrush.api.dto.InquiryDto;
import com.goldrush.api.mapper.InquiryMapper;
import com.goldrush.api.model.Inquiry;
import com.goldrush.api.repository.InquiryRepository;
import org.springframework.stereotype.Service;

@Service
public class InquiryService
    extends BaseService<Inquiry, InquiryDto, InquiryRepository, InquiryMapper> {

  public InquiryService(InquiryRepository repository, InquiryMapper mapper) {
    super(Inquiry.class, repository, mapper);
  }
}
