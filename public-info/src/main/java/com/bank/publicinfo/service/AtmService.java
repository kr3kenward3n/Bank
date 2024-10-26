package com.bank.publicinfo.service;

import com.bank.publicinfo.dto.AtmDto;
import com.bank.publicinfo.entity.AtmEntity;

import java.util.List;

/**
 * Сервис для {@link AtmEntity} и {@link AtmDto}
 */
public interface AtmService {

    /**
     * @param ids технический идентификатор {@link AtmEntity}
     * @return лист {@link AtmDto}
     */
    List<AtmDto> findAllById(List<Long> ids);

    /**
     * @param atm {@link AtmDto}
     * @return {@link AtmDto}
     */
    AtmDto create(AtmDto atm);

    /**
     * @param id  технический идентификатор {@link AtmEntity}
     * @param atm {@link AtmDto}
     * @return {@link AtmDto}
     */
    AtmDto update(Long id, AtmDto atm);

    /**
     * @param id технический идентификатор {@link AtmEntity}
     * @return {@link AtmDto}
     */
    AtmDto findById(Long id);
}
