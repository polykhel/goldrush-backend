package com.goldrush.api.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
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
  private UUID id;

  @Column(updatable = false, nullable = false)
  @CreationTimestamp
  private Instant createdAt;

  @Column(nullable = false)
  @UpdateTimestamp
  private Instant updatedAt;

  @Column(updatable = false)
  @CreatedBy
  private String createdBy;

  @Column @LastModifiedBy private String updatedBy;

  @Column(columnDefinition = "boolean default false")
  private boolean deleted;

  @Column
  private LocalDateTime deletedAt;

  /**
   * Marks the entity as deleted by setting the deletedAt field.
   */
  public void markAsDeleted() {
    this.deletedAt = LocalDateTime.now();
  }

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
