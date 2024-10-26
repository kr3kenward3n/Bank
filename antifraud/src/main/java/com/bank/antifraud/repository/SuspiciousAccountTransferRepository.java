package com.bank.antifraud.repository;

import com.bank.antifraud.entity.SuspiciousAccountTransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для {@link SuspiciousAccountTransferEntity}
 */
public interface SuspiciousAccountTransferRepository extends JpaRepository<SuspiciousAccountTransferEntity, Long> {
}
