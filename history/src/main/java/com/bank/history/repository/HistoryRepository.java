package com.bank.history.repository;

import com.bank.history.entity.HistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * репозиторий {@link HistoryEntity}.
 */
public interface HistoryRepository extends JpaRepository<HistoryEntity, Long> {
}
