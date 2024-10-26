package com.bank.transfer.repository;

import com.bank.transfer.entity.CardTransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для {@link CardTransferEntity}
 */
public interface CardTransferRepository extends JpaRepository<CardTransferEntity, Long> {
}
