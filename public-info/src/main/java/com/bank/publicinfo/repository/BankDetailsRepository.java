package com.bank.publicinfo.repository;

import com.bank.publicinfo.entity.BankDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankDetailsRepository extends JpaRepository<BankDetailsEntity, Long> {
}
