package com.bank.account.repository;

import com.bank.account.entity.AccountDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для {@link AccountDetailsEntity}
 */
public interface AccountDetailsRepository extends JpaRepository<AccountDetailsEntity, Long> {
}
