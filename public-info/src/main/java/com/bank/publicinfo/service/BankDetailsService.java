package com.bank.publicinfo.service;

import com.bank.publicinfo.dto.BankDetailsDto;
import com.bank.publicinfo.entity.BankDetailsEntity;

import java.util.List;

/**
 * Сервис для {@link BankDetailsEntity} и {@link BankDetailsDto}
 */
public interface BankDetailsService {

    /**
     * @param ids технический идентификатор {@link BankDetailsEntity}
     * @return лист {@link BankDetailsDto}
     */
    List<BankDetailsDto> findAllById(List<Long> ids);

    /**
     * @param bankDetails {@link BankDetailsDto}
     * @return {@link BankDetailsDto}
     */
    BankDetailsDto create(BankDetailsDto bankDetails);

    /**
     * @param id          технический идентификатор {@link BankDetailsEntity}
     * @param bankDetails {@link BankDetailsDto}
     * @return {@link BankDetailsDto}
     */
    BankDetailsDto update(Long id, BankDetailsDto bankDetails);

    /**
     * @param id технический идентификатор {@link BankDetailsEntity}
     * @return {@link BankDetailsDto}
     */
    BankDetailsDto findById(Long id);
}
