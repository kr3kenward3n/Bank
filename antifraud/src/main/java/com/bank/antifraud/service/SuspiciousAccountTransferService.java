package com.bank.antifraud.service;

import com.bank.antifraud.dto.SuspiciousAccountTransferDto;
import com.bank.antifraud.entity.SuspiciousAccountTransferEntity;

import java.util.List;

/**
 * Сервис для {@link SuspiciousAccountTransferEntity} и {@link SuspiciousAccountTransferDto}
 */
public interface SuspiciousAccountTransferService {

    /**
     * @param accountTransfer {@link SuspiciousAccountTransferDto}
     * @return {@link SuspiciousAccountTransferDto}
     */
    SuspiciousAccountTransferDto save(SuspiciousAccountTransferDto accountTransfer);

    /**
     * @param id технический идентификатор {@link SuspiciousAccountTransferEntity}
     * @return {@link SuspiciousAccountTransferDto}
     */
    SuspiciousAccountTransferDto findById(Long id);

    /**
     * @param accountTransfer {@link SuspiciousAccountTransferDto}
     * @param id              технический идентификатор {@link SuspiciousAccountTransferEntity}
     * @return {@link SuspiciousAccountTransferDto}
     */
    SuspiciousAccountTransferDto update(Long id, SuspiciousAccountTransferDto accountTransfer);

    /**
     * @param ids список технических идентификаторов {@link SuspiciousAccountTransferEntity}
     * @return лист {@link SuspiciousAccountTransferDto}
     */
    List<SuspiciousAccountTransferDto> findAllById(List<Long> ids);
}
