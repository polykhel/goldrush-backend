package com.goldrush.api.repository;

import com.goldrush.api.model.BaseEntity;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

@NoRepositoryBean
public interface BaseRepository<E extends BaseEntity> extends JpaRepository<E, UUID> {

  @Query("SELECT e FROM #{#entityName} e WHERE e.deleted = false")
  List<E> findAllActive();

  @Query("SELECT e FROM #{#entityName} e WHERE e.id = ?1 AND e.deleted = false")
  Optional<E> findByIdActive(UUID id);

  @Transactional
  @Modifying
  @Query("UPDATE #{#entityName} e SET e.deleted = true WHERE e.id = ?1")
  void markAsDeleted(UUID id);
}
