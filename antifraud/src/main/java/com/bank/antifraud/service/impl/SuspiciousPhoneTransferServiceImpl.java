package com.bank.antifraud.service.impl;

import com.bank.antifraud.dto.SuspiciousPhoneTransferDto;
import com.bank.antifraud.entity.SuspiciousPhoneTransferEntity;
import com.bank.antifraud.mappers.SuspiciousPhoneTransferMapper;
import com.bank.antifraud.repository.SuspiciousPhoneTransferRepository;
import com.bank.antifraud.service.SuspiciousPhoneTransferService;
import com.bank.antifraud.service.common.ExceptionReturner;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Реализация {@link SuspiciousPhoneTransferService}
 */
@Service
@RequiredArgsConstructor
public class SuspiciousPhoneTransferServiceImpl implements SuspiciousPhoneTransferService {

    private static final String MESSAGE = "SuspiciousPhoneTransfer по данному id не существует";

    private final SuspiciousPhoneTransferRepository repository;
    private final SuspiciousPhoneTransferMapper mapper;
    private final ExceptionReturner returner;

    /**
     * @param phoneTransfer {@link SuspiciousPhoneTransferDto}
     * @return {@link SuspiciousPhoneTransferDto}
     */
    @Override
    @Transactional
    public SuspiciousPhoneTransferDto save(SuspiciousPhoneTransferDto phoneTransfer) {

        final SuspiciousPhoneTransferEntity suspiciousTransfer = repository.save(
                mapper.toEntity(phoneTransfer)
        );

        return mapper.toDto(suspiciousTransfer);
    }

    /**
     * @param id технический идентификатор {@link SuspiciousPhoneTransferEntity}
     * @return {@link SuspiciousPhoneTransferDto}
     */
    @Override
    public SuspiciousPhoneTransferDto findById(Long id) {

        final SuspiciousPhoneTransferEntity phoneTransfer = repository.findById(id)
                .orElseThrow(() -> returner.getEntityNotFoundException(MESSAGE));

        return mapper.toDto(phoneTransfer);
    }

    /**
     * @param phoneTransfer {@link SuspiciousPhoneTransferDto}
     * @param id            технический идентификатор {@link SuspiciousPhoneTransferEntity}
     * @return {@link SuspiciousPhoneTransferDto}
     */
    @Override
    @Transactional
    public SuspiciousPhoneTransferDto update(Long id, SuspiciousPhoneTransferDto phoneTransfer) {

        final SuspiciousPhoneTransferEntity suspiciousTransfer = repository.findById(id)
                .orElseThrow(() -> returner.getEntityNotFoundException(MESSAGE));

        final SuspiciousPhoneTransferEntity transfer = mapper.mergeToEntity(phoneTransfer, suspiciousTransfer);

        return mapper.toDto(repository.save(transfer));
    }

    /**
     * @param ids список технический идентификаторов {@link SuspiciousPhoneTransferEntity}
     * @return лист {@link SuspiciousPhoneTransferDto}
     */
    @Override
    public List<SuspiciousPhoneTransferDto> findAllById(List<Long> ids) {

        final List<SuspiciousPhoneTransferEntity> suspiciousPhoneTransfers = ids.stream()
                .map(id -> repository.findById(id)
                        .orElseThrow(() -> returner.getEntityNotFoundException(MESSAGE)))
                .toList();

        return mapper.toListDto(suspiciousPhoneTransfers);
    }
}
