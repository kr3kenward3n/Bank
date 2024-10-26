package com.bank.transfer.repository;

import com.bank.transfer.entity.AccountTransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для {@link AccountTransferEntity}
 */
public interface AccountTransferRepository extends JpaRepository<AccountTransferEntity, Long> {
}
