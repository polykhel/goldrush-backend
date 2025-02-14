package com.goldrush.api.service;

import com.goldrush.api.dto.BaseDto;
import com.goldrush.api.exception.NotFoundException;
import com.goldrush.api.mapper.BaseMapper;
import com.goldrush.api.model.BaseEntity;
import com.goldrush.api.repository.BaseRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public abstract class BaseService<
    E extends BaseEntity,
    D extends BaseDto,
    R extends BaseRepository<E>,
    M extends BaseMapper<E, D>> {

  protected final Class<E> entityClass;
  protected final R repository;
  protected final M mapper;

  protected BaseService(Class<E> entityClass, R repository, M mapper) {
    this.entityClass = entityClass;
    this.repository = repository;
    this.mapper = mapper;
  }

  public D getById(UUID id) {
    return repository
        .findByIdActive(id)
        .map(mapper::toDto)
        .orElseThrow(() -> new NotFoundException(entityClass, id));
  }

  public List<D> getAll() {
    return mapper.toDto(repository.findAllActive());
  }

  @Transactional
  public D save(D toSave) {
    validateBeforeSave(toSave);
    E entity = mapper.toEntity(toSave);
    E saved = repository.save(entity);
    return mapper.toDto(saved);
  }

  @Transactional
  public void deleteById(UUID id) {
    if (!repository.existsById(id)) {
      throw new NotFoundException(entityClass, id);
    }
    repository.markAsDeleted(id);
  }

  protected void validateBeforeSave(@SuppressWarnings("unused") D dto) {
    // override in subclasses for custom validation
  }
}
