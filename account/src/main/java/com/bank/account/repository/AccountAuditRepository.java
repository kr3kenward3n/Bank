package com.bank.account.repository;

import com.bank.account.entity.AuditEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для {@link AuditEntity}
 */
public interface AccountAuditRepository extends JpaRepository<AuditEntity, Long> {
}
