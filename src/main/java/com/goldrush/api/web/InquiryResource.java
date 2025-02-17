package com.goldrush.api.web;

import com.goldrush.api.dto.InquiryDto;
import com.goldrush.api.dto.request.InquiryParamsDto;
import com.goldrush.api.dto.response.InquiryStatusDto;
import com.goldrush.api.service.InquiryService;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inquiry")
public class InquiryResource {

  private final InquiryService inquiryService;

  public InquiryResource(InquiryService inquiryService) {
    this.inquiryService = inquiryService;
  }

  /**
   * Retrieves a paginated list of inquiries based on provided parameters.
   *
   * @param params the filtering and querying parameters for inquiries
   * @param pageable the pagination information including page number and size
   * @return a paginated list of inquiry DTOs that match the specified parameters
   */
  @GetMapping
  public Page<InquiryDto> getAlInquiries(InquiryParamsDto params, Pageable pageable) {
    return inquiryService.getInquiries(params, pageable);
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

  @GetMapping("/statuses")
  public List<InquiryStatusDto> getInquiryStatuses() {
    return inquiryService.getInquiryStatuses();
  }
}
