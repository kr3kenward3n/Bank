package com.bank.profile.repository;

import com.bank.profile.entity.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для {@link ProfileEntity}
 */
public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {
}
