package com.goldrush.api.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id", updatable = false, nullable = false, columnDefinition = "UUID default gen_random_uuid()")
  private UUID id;

  @Column(updatable = false, nullable = false, columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
  @CreationTimestamp
  private Instant createdAt;

  @Column(nullable = false, columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP")
  @UpdateTimestamp
  private Instant updatedAt;

  @Column(updatable = false, columnDefinition = "VARCHAR(255) default 'SYSTEM'")
  @CreatedBy
  private String createdBy;

  @Column(columnDefinition = "VARCHAR(255) default 'SYSTEM'")
  @LastModifiedBy
  private String updatedBy;

  @Column(columnDefinition = "boolean default false")
  private boolean deleted;

  @Column(columnDefinition = "TIMESTAMP")
  private Instant deletedAt;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BaseEntity that = (BaseEntity) o;
    return id != null && id.equals(that.id);
  }

  @Override
  public int hashCode() {
    return 31;
  }
}
