package com.bank.antifraud.service.impl;

import com.bank.antifraud.dto.SuspiciousCardTransferDto;
import com.bank.antifraud.entity.SuspiciousCardTransferEntity;
import com.bank.antifraud.mappers.SuspiciousCardTransferMapper;
import com.bank.antifraud.repository.SuspiciousCardTransferRepository;
import com.bank.antifraud.service.SuspiciousCardTransferService;
import com.bank.antifraud.service.common.ExceptionReturner;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Реализация {@link SuspiciousCardTransferService}
 */
@Service
@RequiredArgsConstructor
public class SuspiciousCardTransferServiceImpl implements SuspiciousCardTransferService {

    private static final String MESSAGE = "SuspiciousCardTransfer по данному id не существует";

    private final SuspiciousCardTransferRepository repository;
    private final SuspiciousCardTransferMapper mapper;
    private final ExceptionReturner returner;

    /**
     * @param cardTransfer {@link SuspiciousCardTransferDto}
     * @return {@link SuspiciousCardTransferDto}
     */
    @Override
    @Transactional
    public SuspiciousCardTransferDto save(SuspiciousCardTransferDto cardTransfer) {

        final SuspiciousCardTransferEntity suspiciousTransfer = repository.save(
                mapper.toEntity(cardTransfer)
        );

        return mapper.toDto(suspiciousTransfer);
    }

    /**
     * @param id технический идентификатор {@link SuspiciousCardTransferEntity}
     * @return {@link SuspiciousCardTransferDto}
     */
    @Override
    public SuspiciousCardTransferDto findById(Long id) {
        final SuspiciousCardTransferEntity cardTransfer = repository.findById(id)
                .orElseThrow(() -> returner.getEntityNotFoundException(MESSAGE));

        return mapper.toDto(cardTransfer);
    }

    /**
     * @param cardTransfer {@link SuspiciousCardTransferDto}
     * @param id           технический идентификатор {@link SuspiciousCardTransferEntity}
     * @return {@link SuspiciousCardTransferDto}
     */
    @Override
    @Transactional
    public SuspiciousCardTransferDto update(Long id, SuspiciousCardTransferDto cardTransfer) {

        final SuspiciousCardTransferEntity suspiciousTransfer = repository.findById(id)
                .orElseThrow(() -> returner.getEntityNotFoundException(MESSAGE));

        final SuspiciousCardTransferEntity transfer = mapper.mergeToEntity(cardTransfer, suspiciousTransfer);

        return mapper.toDto(repository.save(transfer));
    }

    /**
     * @param ids список технических идентификаторов {@link SuspiciousCardTransferEntity}
     * @return лист {@link SuspiciousCardTransferDto}
     */
    @Override
    public List<SuspiciousCardTransferDto> findAllById(List<Long> ids) {

        final List<SuspiciousCardTransferEntity> suspiciousCardTransfers = ids.stream()
                .map(id -> repository.findById(id)
                        .orElseThrow(() -> returner.getEntityNotFoundException(MESSAGE)))
                .toList();

        return mapper.toListDto(suspiciousCardTransfers);
    }
}
