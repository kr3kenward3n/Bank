package com.bank.profile.repository;

import com.bank.profile.entity.RegistrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для {@link RegistrationEntity}
 */
public interface RegistrationRepository extends JpaRepository<RegistrationEntity, Long> {
}
