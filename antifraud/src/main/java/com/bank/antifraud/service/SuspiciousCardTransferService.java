package com.bank.antifraud.service;

import com.bank.antifraud.dto.SuspiciousCardTransferDto;
import com.bank.antifraud.entity.SuspiciousCardTransferEntity;

import java.util.List;

/**
 * Сервис для {@link SuspiciousCardTransferEntity} и {@link SuspiciousCardTransferDto }
 */
public interface SuspiciousCardTransferService {

    /**
     * @param cardTransfer {@link SuspiciousCardTransferDto}
     * @return {@link SuspiciousCardTransferDto}
     */
    SuspiciousCardTransferDto save(SuspiciousCardTransferDto cardTransfer);

    /**
     * @param id технический идентификатор {@link SuspiciousCardTransferEntity}
     * @return {@link SuspiciousCardTransferDto}
     */
    SuspiciousCardTransferDto findById(Long id);

    /**
     * @param cardTransfer {@link SuspiciousCardTransferDto}
     * @param id           технический идентификатор {@link SuspiciousCardTransferEntity}
     * @return {@link SuspiciousCardTransferDto}
     */
    SuspiciousCardTransferDto update(Long id, SuspiciousCardTransferDto cardTransfer);

    /**
     * @param ids список технических идентификаторов {@link SuspiciousCardTransferEntity}
     * @return лист {@link SuspiciousCardTransferDto}
     */
    List<SuspiciousCardTransferDto> findAllById(List<Long> ids);
}
