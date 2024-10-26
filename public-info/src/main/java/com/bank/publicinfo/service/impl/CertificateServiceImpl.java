package com.bank.publicinfo.service.impl;

import com.bank.publicinfo.dto.CertificateDto;
import com.bank.publicinfo.entity.CertificateEntity;
import com.bank.publicinfo.mapper.CertificateMapper;
import com.bank.publicinfo.repository.CertificateRepository;
import com.bank.publicinfo.service.CertificateService;
import com.bank.publicinfo.util.EntityNotFoundSupplier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Реализация {@link CertificateService}
 */
@Service
@RequiredArgsConstructor
public class CertificateServiceImpl implements CertificateService {

    private final static String MESSAGE = "Сертификата не найдено с id ";

    private final CertificateRepository repository;
    private final CertificateMapper mapper;
    private final EntityNotFoundSupplier supplierNotFound;

    /**
     * @param ids список технических идентификаторов {@link CertificateEntity}
     * @return лист {@link CertificateDto}
     */
    @Override
    public List<CertificateDto> findAllById(List<Long> ids) {
        final List<CertificateEntity> certificates = repository.findAllById(ids);
        supplierNotFound.checkForSizeAndLogging(MESSAGE, ids, certificates);
        return mapper.toDtoList(certificates);
    }

    /**
     * @param certificateDto {@link CertificateDto}
     * @return {@link CertificateDto}
     */
    @Override
    @Transactional
    public CertificateDto create(CertificateDto certificateDto) {
        final CertificateEntity certificate = repository.save(mapper.toEntity(certificateDto));
        return mapper.toDto(certificate);
    }

    /**
     * @param certificate {@link CertificateDto}
     * @return {@link CertificateDto}
     */
    @Override
    @Transactional
    public CertificateDto update(Long id, CertificateDto certificate) {
        final CertificateEntity entity = repository.findById(id)
                .orElseThrow(() -> (
                        supplierNotFound.getException(MESSAGE, id)
                ));

        final CertificateEntity updatedCertificate = mapper.mergeToEntity(certificate, entity);
        return mapper.toDto(updatedCertificate);
    }

    /**
     * @param id технический идентификатор {@link CertificateEntity}
     * @return {@link CertificateDto}
     */
    @Override
    public CertificateDto findById(Long id) {
        return mapper.toDto(repository.findById(id)
                .orElseThrow(() -> supplierNotFound.getException(MESSAGE, id)));
    }
}
