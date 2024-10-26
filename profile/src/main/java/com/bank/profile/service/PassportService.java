package com.bank.profile.service;

import com.bank.profile.dto.PassportDto;
import com.bank.profile.entity.PassportEntity;

import java.util.List;

/**
 * Сервис {@link PassportEntity} {@link PassportDto}
 */
public interface PassportService {

    /**
     * @param id технический идентификатор {@link PassportEntity}
     * @return {@link PassportDto}
     */
    PassportDto findById(Long id);

    /**
     * @param ids лист технических идентификаторов {@link PassportEntity}
     * @return {@link List<PassportDto>}
     */
    List<PassportDto> findAllById(List<Long> ids);

    /**
     * @param passport {@link PassportDto}
     * @return {@link PassportDto}
     */
    PassportDto save(PassportDto passport);

    /**
     * @param passport {@link PassportDto}
     * @param id       технический идентификатор {@link PassportEntity}
     * @return {@link PassportDto}
     */
    PassportDto update(Long id, PassportDto passport);
}
