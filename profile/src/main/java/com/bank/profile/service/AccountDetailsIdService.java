package com.bank.profile.service;

import com.bank.profile.dto.AccountDetailsIdDto;
import com.bank.profile.entity.AccountDetailsIdEntity;

import java.util.List;

/**
 * Сервис {@link AccountDetailsIdEntity} {@link AccountDetailsIdDto}
 */
public interface AccountDetailsIdService {

    /**
     * @param id технический идентификатор {@link AccountDetailsIdEntity}
     * @return {@link AccountDetailsIdDto}
     */
    AccountDetailsIdDto findById(Long id);

    /**
     * @param ids лист технических идентификаторов {@link AccountDetailsIdEntity}
     * @return {@link List<AccountDetailsIdDto>}
     */
    List<AccountDetailsIdDto> findAllById(List<Long> ids);

    /**
     * @param accountDetailsId {@link AccountDetailsIdDto}
     * @return {@link AccountDetailsIdDto}
     */
    AccountDetailsIdDto save(AccountDetailsIdDto accountDetailsId);

    /**
     * @param accountDetailsId {@link AccountDetailsIdDto}
     * @param id               технический идентификатор {@link AccountDetailsIdEntity}
     * @return {@link AccountDetailsIdDto}
     */
    AccountDetailsIdDto update(Long id, AccountDetailsIdDto accountDetailsId);
}
