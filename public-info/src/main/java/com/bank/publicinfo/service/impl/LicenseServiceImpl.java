package com.bank.publicinfo.service.impl;

import com.bank.publicinfo.dto.LicenseDto;
import com.bank.publicinfo.entity.LicenseEntity;
import com.bank.publicinfo.mapper.LicenseMapper;
import com.bank.publicinfo.repository.LicenseRepository;
import com.bank.publicinfo.service.LicenseService;
import com.bank.publicinfo.util.EntityNotFoundSupplier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Реализация {@link LicenseService}
 */
@Service
@RequiredArgsConstructor
public class LicenseServiceImpl implements LicenseService {

    private final static String MESSAGE = "Лицензии не найдено с id ";

    private final LicenseRepository repository;
    private final LicenseMapper mapper;
    private final EntityNotFoundSupplier supplierNotFound;

    /**
     * @param ids технический идентификатор {@link LicenseEntity}
     * @return лист {@link LicenseDto}
     */
    @Override
    public List<LicenseDto> findAllById(List<Long> ids) {
        final List<LicenseEntity> licenses = repository.findAllById(ids);
        supplierNotFound.checkForSizeAndLogging(MESSAGE, ids, licenses);
        return mapper.toDtoList(licenses);
    }

    /**
     * @param licenseDto {@link LicenseDto}
     * @return {@link LicenseDto}
     */
    @Override
    @Transactional
    public LicenseDto create(LicenseDto licenseDto) {
        final LicenseEntity license = repository.save(mapper.toEntity(licenseDto));
        return mapper.toDto(license);
    }

    /**
     * @param license {@link LicenseDto}
     * @return {@link LicenseDto}
     */
    @Override
    @Transactional
    public LicenseDto update(Long id, LicenseDto license) {
        final LicenseEntity entity = repository.findById(id)
                .orElseThrow(() -> (
                        supplierNotFound.getException(MESSAGE, id)
                ));

        final LicenseEntity updatedLicense = mapper.mergeToEntity(license, entity);
        return mapper.toDto(updatedLicense);
    }

    /**
     * @param id технический идентификатор {@link LicenseEntity}
     * @return {@link LicenseDto}
     */
    @Override
    public LicenseDto findById(Long id) {
        return mapper.toDto(repository.findById(id)
                .orElseThrow(() -> supplierNotFound.getException(MESSAGE, id)));
    }
}
