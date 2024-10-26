package com.bank.profile.repository;

import com.bank.profile.entity.AccountDetailsIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для {@link AccountDetailsIdEntity}
 */
public interface AccountDetailsIdRepository extends JpaRepository<AccountDetailsIdEntity, Long> {
}
