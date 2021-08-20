package dev.orion.api.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.GenericGenerator;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@ApplicationScoped
@Table(name = "TEMPLATE")
public class Template extends PanacheEntityBase {
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(columnDefinition = "BINARY(16)")
  public UUID uuid;

  @Column(nullable = false)
  public String title;

  @Column(nullable = false)
  public String msg;

  @ColumnTransformer
  public LocalDateTime createdAt;

  @Column
  public LocalDateTime updatedAt;

  @Version
  public int version;

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  @PrePersist
  void createdAt() {
    this.createdAt = this.updatedAt = LocalDateTime.now();
  }

  @PreUpdate
  void updatedAt() {
    this.updatedAt = LocalDateTime.now();
  }
}
