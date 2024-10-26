package com.bank.publicinfo.service.impl;

import com.bank.publicinfo.dto.BankDetailsDto;
import com.bank.publicinfo.entity.BankDetailsEntity;
import com.bank.publicinfo.mapper.BankDetailsMapper;
import com.bank.publicinfo.repository.BankDetailsRepository;
import com.bank.publicinfo.service.BankDetailsService;
import com.bank.publicinfo.util.EntityNotFoundSupplier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Реализация {@link BankDetailsService}
 */
@Service
@RequiredArgsConstructor
public class BankDetailsServiceImpl implements BankDetailsService {

    private final static String MESSAGE = "Информации о банке не найдено с id ";

    private final BankDetailsRepository repository;
    private final BankDetailsMapper mapper;
    private final EntityNotFoundSupplier supplierNotFound;

    /**
     * @param ids список техничских идентификаторов {@link BankDetailsEntity}
     * @return лист {@link BankDetailsDto}
     */
    @Override
    public List<BankDetailsDto> findAllById(List<Long> ids) {
        final List<BankDetailsEntity> bankDetails = repository.findAllById(ids);
        supplierNotFound.checkForSizeAndLogging(MESSAGE, ids, bankDetails);
        return mapper.toDtoList(bankDetails);
    }

    /**
     * @param bankDetailsDto {@link BankDetailsDto} общедоступной информации
     * @return {@link BankDetailsDto}
     */
    @Override
    @Transactional
    public BankDetailsDto create(BankDetailsDto bankDetailsDto) {
        final BankDetailsEntity bankDetails = repository.save(mapper.toEntity(bankDetailsDto));
        return mapper.toDto(bankDetails);
    }

    /**
     * @param bankDetails {@link BankDetailsDto}
     * @return {@link BankDetailsDto}
     */
    @Override
    @Transactional
    public BankDetailsDto update(Long id, BankDetailsDto bankDetails) {
        final BankDetailsEntity entity = repository.findById(id)
                .orElseThrow(() -> (
                        supplierNotFound.getException(MESSAGE, id)
                ));

        final BankDetailsEntity updatedDetails = mapper.mergeToEntity(bankDetails, entity);
        return mapper.toDto(updatedDetails);
    }

    /**
     * @param id технический идентификатор {@link BankDetailsEntity}
     * @return {@link BankDetailsDto}
     */
    @Override
    public BankDetailsDto findById(Long id) {
        return mapper.toDto(repository.findById(id)
                .orElseThrow(() -> supplierNotFound.getException(MESSAGE, id)));
    }
}
