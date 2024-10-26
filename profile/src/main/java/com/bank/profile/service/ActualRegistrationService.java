package com.bank.profile.service;

import com.bank.profile.dto.ActualRegistrationDto;
import com.bank.profile.entity.ActualRegistrationEntity;

import java.util.List;

/**
 * Сервис {@link ActualRegistrationEntity} {@link ActualRegistrationDto}
 */
public interface ActualRegistrationService {

    /**
     * @param id технический идентификатор {@link ActualRegistrationEntity}
     * @return {@link ActualRegistrationDto}
     */
    ActualRegistrationDto findById(Long id);

    /**
     * @param ids лист технических идентификаторов {@link ActualRegistrationEntity}
     * @return {@link List<ActualRegistrationDto>}
     */
    List<ActualRegistrationDto> findAllById(List<Long> ids);

    /**
     * @param actualRegistration {@link ActualRegistrationDto}
     * @return {@link ActualRegistrationDto}
     */
    ActualRegistrationDto save(ActualRegistrationDto actualRegistration);

    /**
     * @param actualRegistration {@link ActualRegistrationDto}
     * @param id                 технический идентификатор {@link ActualRegistrationEntity}
     * @return {@link ActualRegistrationDto}
     */
    ActualRegistrationDto update(Long id, ActualRegistrationDto actualRegistration);
}
