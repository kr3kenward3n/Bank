package com.bank.publicinfo.repository;

import com.bank.publicinfo.entity.AtmEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtmRepository extends JpaRepository<AtmEntity, Long> {
}
