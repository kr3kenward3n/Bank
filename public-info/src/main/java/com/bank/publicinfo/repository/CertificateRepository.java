package com.bank.publicinfo.repository;

import com.bank.publicinfo.entity.CertificateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificateRepository extends JpaRepository<CertificateEntity, Long> {
}
