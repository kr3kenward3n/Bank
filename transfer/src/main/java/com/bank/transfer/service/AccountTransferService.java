package com.bank.transfer.service;

import com.bank.transfer.dto.AccountTransferDto;
import com.bank.transfer.entity.AccountTransferEntity;

import java.util.List;

/**
 * Сервис для {@link AccountTransferEntity} и {@link AccountTransferDto}
 */
public interface AccountTransferService {

    /**
     * @param ids список технических идентификаторов {@link AccountTransferEntity}
     * @return лист {@link AccountTransferDto}
     */
    List<AccountTransferDto> findAllById(List<Long> ids);

    /**
     * @param id технический идентификатор {@link AccountTransferEntity}
     * @return {@link AccountTransferDto}
     */
    AccountTransferDto findById(Long id);

    /**
     * @param accountTransfer {@link AccountTransferDto}
     * @return {@link AccountTransferDto}
     */
    AccountTransferDto save(AccountTransferDto accountTransfer);

    /**
     * @param accountTransfer {@link AccountTransferDto}
     * @param id              технический идентификатор {@link AccountTransferEntity}
     * @return {@link AccountTransferDto}
     */
    AccountTransferDto update(Long id, AccountTransferDto accountTransfer);
}
