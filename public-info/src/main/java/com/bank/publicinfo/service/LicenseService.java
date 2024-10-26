package com.bank.publicinfo.service;

import com.bank.publicinfo.entity.LicenseEntity;
import com.bank.publicinfo.dto.LicenseDto;

import java.util.List;

/**
 * Сервис для {@link LicenseEntity} и {@link LicenseDto}
 */
public interface LicenseService {

    /**
     * @param ids технический идентификатор {@link LicenseEntity}
     * @return лист {@link LicenseDto}
     */
    List<LicenseDto> findAllById(List<Long> ids);

    /**
     * @param license {@link LicenseDto}
     * @return {@link LicenseDto}
     */
    LicenseDto create(LicenseDto license);

    /**
     * @param id      технический идентификатор {@link LicenseEntity}
     * @param license {@link LicenseDto}
     * @return {@link LicenseDto}
     */
    LicenseDto update(Long id, LicenseDto license);

    /**
     * @param id технический идентификатор {@link LicenseEntity}
     * @return {@link LicenseDto}
     */
    LicenseDto findById(Long id);
}
