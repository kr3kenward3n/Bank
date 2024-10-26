package com.bank.profile.repository;

import com.bank.profile.entity.ActualRegistrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для {@link ActualRegistrationEntity}
 */
public interface ActualRegistrationRepository extends JpaRepository<ActualRegistrationEntity, Long> {
}
