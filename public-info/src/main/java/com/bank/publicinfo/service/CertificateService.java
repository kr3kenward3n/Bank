package com.bank.publicinfo.service;

import com.bank.publicinfo.dto.CertificateDto;
import com.bank.publicinfo.entity.CertificateEntity;

import java.util.List;

/**
 * Сервис для {@link CertificateEntity} и {@link CertificateDto}
 */
public interface CertificateService {

    /**
     * @param ids технический идентификатор {@link CertificateEntity}
     * @return лист {@link CertificateDto}
     */
    List<CertificateDto> findAllById(List<Long> ids);

    /**
     * @param certificate {@link CertificateDto}
     * @return {@link CertificateDto}
     */
    CertificateDto create(CertificateDto certificate);

    /**
     * @param id          технический идентификатор {@link CertificateEntity}
     * @param certificate {@link CertificateDto}
     * @return {@link CertificateDto}
     */
    CertificateDto update(Long id, CertificateDto certificate);

    /**
     * @param id технический идентификатор {@link CertificateEntity}
     * @return {@link CertificateDto}
     */
    CertificateDto findById(Long id);
}
