package com.goldrush.api.mapper;

import com.goldrush.api.model.BaseEntity;
import com.goldrush.api.dto.BaseDto;
import java.util.List;

public interface BaseMapper<E extends BaseEntity, D extends BaseDto> {

  E toEntity(D country);

  D toDto(E country);

  List<E> toEntity(List<D> countries);

  List<D> toDto(List<E> countries);
}
