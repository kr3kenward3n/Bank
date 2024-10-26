package com.bank.antifraud.service.impl;

import com.bank.antifraud.dto.SuspiciousAccountTransferDto;
import com.bank.antifraud.entity.SuspiciousAccountTransferEntity;
import com.bank.antifraud.mappers.SuspiciousAccountTransferMapper;
import com.bank.antifraud.repository.SuspiciousAccountTransferRepository;
import com.bank.antifraud.service.SuspiciousAccountTransferService;
import com.bank.antifraud.service.common.ExceptionReturner;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Реализация {@link SuspiciousAccountTransferService}
 */
@Service
@RequiredArgsConstructor
public class SuspiciousAccountTransferServiceImpl implements SuspiciousAccountTransferService {

    private static final String MESSAGE = "SuspiciousAccountTransfer по данному id не существует";

    private final SuspiciousAccountTransferRepository repository;
    private final SuspiciousAccountTransferMapper mapper;
    private final ExceptionReturner returner;

    /**
     * @param accountTransfer {@link SuspiciousAccountTransferDto}
     * @return {@link SuspiciousAccountTransferDto}
     */
    @Override
    @Transactional
    public SuspiciousAccountTransferDto save(SuspiciousAccountTransferDto accountTransfer) {

        final SuspiciousAccountTransferEntity suspiciousTransfer = repository.save(
                mapper.toEntity(accountTransfer)
        );

        return mapper.toDto(suspiciousTransfer);
    }

    /**
     * @param id технический идентификатор {@link SuspiciousAccountTransferEntity}
     * @return {@link SuspiciousAccountTransferDto}
     */
    @Override
    public SuspiciousAccountTransferDto findById(Long id) {
        final SuspiciousAccountTransferEntity accountTransfer = repository.findById(id)
                .orElseThrow(() -> returner.getEntityNotFoundException(MESSAGE));

        return mapper.toDto(accountTransfer);
    }

    /**
     * @param accountTransfer {@link SuspiciousAccountTransferDto}
     * @param id              технический идентификатор {@link SuspiciousAccountTransferEntity}
     * @return {@link SuspiciousAccountTransferDto}
     */
    @Override
    @Transactional
    public SuspiciousAccountTransferDto update(Long id, SuspiciousAccountTransferDto accountTransfer) {

        final SuspiciousAccountTransferEntity suspiciousTransfer = repository.findById(id)
                .orElseThrow(() -> returner.getEntityNotFoundException(MESSAGE));

        final SuspiciousAccountTransferEntity transfer = mapper.mergeToEntity(accountTransfer, suspiciousTransfer);

        return mapper.toDto(repository.save(transfer));
    }

    /**
     * @param ids список технических идентификаторов {@link SuspiciousAccountTransferEntity}
     * @return лист {@link SuspiciousAccountTransferDto}
     */
    @Override
    public List<SuspiciousAccountTransferDto> findAllById(List<Long> ids) {

        final List<SuspiciousAccountTransferEntity> suspiciousAccountTransfers = ids.stream()
                .map(id -> repository.findById(id)
                        .orElseThrow(() -> returner.getEntityNotFoundException(MESSAGE)))
                .toList();

        return mapper.toListDto(suspiciousAccountTransfers);
    }
}
