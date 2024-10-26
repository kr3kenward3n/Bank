package com.bank.antifraud.service;

import com.bank.antifraud.dto.SuspiciousPhoneTransferDto;
import com.bank.antifraud.entity.SuspiciousPhoneTransferEntity;

import java.util.List;

/**
 * Сервис для {@link SuspiciousPhoneTransferEntity} и {@link SuspiciousPhoneTransferDto}
 */
public interface SuspiciousPhoneTransferService {

    /**
     * @param phoneTransfer {@link SuspiciousPhoneTransferDto}
     * @return {@link SuspiciousPhoneTransferDto}
     */
    SuspiciousPhoneTransferDto save(SuspiciousPhoneTransferDto phoneTransfer);

    /**
     * @param id технический идентификатор {@link SuspiciousPhoneTransferEntity}
     * @return {@link SuspiciousPhoneTransferDto}
     */
    SuspiciousPhoneTransferDto findById(Long id);

    /**
     * @param phoneTransfer {@link SuspiciousPhoneTransferDto}
     * @param id            технический идентификатор {@link SuspiciousPhoneTransferEntity}
     * @return {@link SuspiciousPhoneTransferDto}
     */
    SuspiciousPhoneTransferDto update(Long id, SuspiciousPhoneTransferDto phoneTransfer);

    /**
     * @param ids список технический идентификаторов {@link SuspiciousPhoneTransferEntity}
     * @return лист {@link SuspiciousPhoneTransferDto}
     */
    List<SuspiciousPhoneTransferDto> findAllById(List<Long> ids);
}
