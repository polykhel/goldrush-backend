package com.goldrush.api.mapper;

import com.goldrush.api.model.Provider;
import com.goldrush.api.dto.ProviderDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProviderMapper extends BaseMapper<Provider, ProviderDto> {}
