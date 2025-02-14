package com.goldrush.api.web;

import com.goldrush.api.dto.InquiryDto;
import com.goldrush.api.service.InquiryService;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inquiry")
public class InquiryResource {

  private final InquiryService inquiryService;

  public InquiryResource(InquiryService inquiryService) {
    this.inquiryService = inquiryService;
  }

  @GetMapping
  public List<InquiryDto> getAlInquiries() {
    return inquiryService.getAll();
  }

  @GetMapping("/{id}")
  public InquiryDto getInquiryById(@PathVariable UUID id) {
    return inquiryService.getById(id);
  }

  @PostMapping
  public InquiryDto saveInquiry(InquiryDto inquiryDto) {
    return inquiryService.save(inquiryDto);
  }

  @DeleteMapping("/{id}")
  public void deleteInquiry(@PathVariable UUID id) {
    inquiryService.deleteById(id);
  }
}
