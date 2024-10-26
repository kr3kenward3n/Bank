package com.bank.antifraud.repository;

import com.bank.antifraud.entity.SuspiciousPhoneTransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для {@link SuspiciousPhoneTransferEntity}
 */
public interface SuspiciousPhoneTransferRepository extends JpaRepository<SuspiciousPhoneTransferEntity, Long> {
}
