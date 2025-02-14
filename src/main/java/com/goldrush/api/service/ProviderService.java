package com.goldrush.api.service;

import com.goldrush.api.dto.ProviderDto;
import com.goldrush.api.mapper.ProviderMapper;
import com.goldrush.api.model.Provider;
import com.goldrush.api.repository.ProviderRepository;
import org.springframework.stereotype.Service;

@Service
public class ProviderService
    extends BaseService<Provider, ProviderDto, ProviderRepository, ProviderMapper> {

  public ProviderService(ProviderRepository repository, ProviderMapper mapper) {
    super(Provider.class, repository, mapper);
  }
}
