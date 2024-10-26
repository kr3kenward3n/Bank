package com.bank.publicinfo.repository;

import com.bank.publicinfo.entity.AuditEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для {@link AuditEntity}
 */
public interface AuditRepository extends JpaRepository<AuditEntity, Long> {
}
