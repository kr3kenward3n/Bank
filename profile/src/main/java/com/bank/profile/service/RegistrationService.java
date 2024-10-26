package com.bank.profile.service;

import com.bank.profile.dto.RegistrationDto;
import com.bank.profile.entity.RegistrationEntity;

import java.util.List;

/**
 * Сервис {@link RegistrationEntity} {@link RegistrationDto}
 */
public interface RegistrationService {

    /**
     * @param id технический идентификатор {@link RegistrationEntity}
     * @return {@link RegistrationDto}
     */
    RegistrationDto findById(Long id);

    /**
     * @param ids лист технических идентификаторов {@link RegistrationEntity}
     * @return {@link List<RegistrationDto>}
     */
    List<RegistrationDto> findAllById(List<Long> ids);

    /**
     * @param registration {@link RegistrationDto}
     * @return {@link RegistrationDto}
     */
    RegistrationDto save(RegistrationDto registration);

    /**
     * @param registration {@link RegistrationDto}
     * @param id           технический идентификатор {@link RegistrationEntity}
     * @return {@link RegistrationDto}
     */
    RegistrationDto update(Long id, RegistrationDto registration);
}
