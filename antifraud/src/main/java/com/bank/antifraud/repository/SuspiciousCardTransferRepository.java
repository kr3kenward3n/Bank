package com.bank.antifraud.repository;

import com.bank.antifraud.entity.SuspiciousCardTransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для {@link SuspiciousCardTransferEntity}
 */
public interface SuspiciousCardTransferRepository extends JpaRepository<SuspiciousCardTransferEntity, Long> {
}
