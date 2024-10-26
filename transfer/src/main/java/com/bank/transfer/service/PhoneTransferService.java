package com.bank.transfer.service;

import com.bank.transfer.dto.PhoneTransferDto;
import com.bank.transfer.entity.PhoneTransferEntity;

import java.util.List;

/**
 * Сервис для {@link PhoneTransferEntity} и {@link PhoneTransferDto}
 */
public interface PhoneTransferService {

    /**
     * @param ids список технических идентификаторов {@link PhoneTransferEntity}
     * @return лист {@link PhoneTransferDto}
     */
    List<PhoneTransferDto> findAllById(List<Long> ids);

    /**
     * @param id технический идентификатор {@link PhoneTransferEntity}
     * @return {@link PhoneTransferDto}
     */
    PhoneTransferDto findById(Long id);

    /**
     * @param phoneTransfer {@link PhoneTransferDto}
     * @return {@link PhoneTransferDto}
     */
    PhoneTransferDto save(PhoneTransferDto phoneTransfer);

    /**
     * @param phoneTransfer {@link PhoneTransferDto}
     * @param id            технический идентификатор {@link PhoneTransferEntity}
     * @return {@link PhoneTransferDto}
     */
    PhoneTransferDto update(Long id, PhoneTransferDto phoneTransfer);
}
