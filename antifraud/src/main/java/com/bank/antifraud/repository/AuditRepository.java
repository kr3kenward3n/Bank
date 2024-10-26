package com.bank.antifraud.repository;

import com.bank.antifraud.entity.AuditEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для {@link AuditEntity}
 */
public interface AuditRepository extends JpaRepository<AuditEntity, Long> {
}
