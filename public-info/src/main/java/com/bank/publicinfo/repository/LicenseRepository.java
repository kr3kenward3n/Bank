package com.bank.publicinfo.repository;

import com.bank.publicinfo.entity.LicenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LicenseRepository extends JpaRepository<LicenseEntity, Long> {
}
