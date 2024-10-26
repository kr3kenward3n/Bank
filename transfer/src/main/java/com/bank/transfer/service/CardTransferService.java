package com.bank.transfer.service;

import com.bank.transfer.dto.CardTransferDto;
import com.bank.transfer.entity.CardTransferEntity;

import java.util.List;

/**
 * Сервис для {@link CardTransferEntity} и {@link CardTransferDto}
 */
public interface CardTransferService {

    /**
     * @param ids список технических идентификаторов {@link CardTransferEntity}
     * @return лист {@link CardTransferDto}
     */
    List<CardTransferDto> findAllById(List<Long> ids);

    /**
     * @param id технический идентификатор {@link CardTransferEntity}
     * @return {@link CardTransferDto}
     */
    CardTransferDto findById(Long id);

    /**
     * @param cardTransfer {@link CardTransferDto}
     * @return {@link CardTransferDto}
     */
    CardTransferDto save(CardTransferDto cardTransfer);

    /**
     * @param cardTransfer {@link CardTransferDto}
     * @param id           технический идентификатор {@link CardTransferEntity}
     * @return {@link CardTransferDto}
     */
    CardTransferDto update(Long id, CardTransferDto cardTransfer);
}
