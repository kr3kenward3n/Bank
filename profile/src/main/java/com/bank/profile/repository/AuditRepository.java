package com.bank.profile.repository;

import com.bank.profile.entity.AuditEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для {@link AuditEntity}
 */
public interface AuditRepository extends JpaRepository<AuditEntity, Long> {
}
