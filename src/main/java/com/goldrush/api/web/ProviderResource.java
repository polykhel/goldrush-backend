package com.goldrush.api.web;

import com.goldrush.api.dto.ProviderDto;
import com.goldrush.api.service.ProviderService;
import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/provider")
public class ProviderResource {

  private final ProviderService providerService;

  public ProviderResource(ProviderService providerService) {
    this.providerService = providerService;
  }

  @GetMapping
  public List<ProviderDto> getAlProviders() {
    return providerService.getAll();
  }

  @GetMapping("/{id}")
  public ProviderDto getProviderById(@PathVariable UUID id) {
    return providerService.getById(id);
  }
}
