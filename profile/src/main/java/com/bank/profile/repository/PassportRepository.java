package com.bank.profile.repository;

import com.bank.profile.entity.PassportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для {@link PassportEntity}
 */
public interface PassportRepository extends JpaRepository<PassportEntity, Long> {
}
