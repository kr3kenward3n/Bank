package com.bank.account.service;

import com.bank.account.dto.AccountDetailsDto;
import com.bank.account.entity.AccountDetailsEntity;

import java.util.List;

/**
 * Сервис для {@link AccountDetailsEntity} {@link AccountDetailsDto}
 */
public interface AccountDetailsService {

    /**
     * @param id технический идентификатор {@link AccountDetailsEntity}
     * @return {@link AccountDetailsDto}
     */
    AccountDetailsDto findById(Long id);

    /**
     * @param ids лист технических идентификаторов {@link AccountDetailsEntity}
     * @return {@link List<AccountDetailsDto>}
     */
    List<AccountDetailsDto> findAllById(List<Long> ids);

    /**
     * @param accountDetails {@link AccountDetailsDto}
     * @return {@link AccountDetailsDto}
     */
    AccountDetailsDto save(AccountDetailsDto accountDetails);

    /**
     * @param accountDetails {@link AccountDetailsDto}
     * @param id             технический идентификатор {@link AccountDetailsEntity}
     * @return {@link AccountDetailsDto}
     */
    AccountDetailsDto update(Long id, AccountDetailsDto accountDetails);
}
