package com.bank.transfer.service.Impl;

import com.bank.transfer.dto.PhoneTransferDto;
import com.bank.transfer.mapper.PhoneTransferMapper;
import com.bank.transfer.repository.PhoneTransferRepository;
import com.bank.transfer.entity.PhoneTransferEntity;
import com.bank.transfer.service.PhoneTransferService;
import com.bank.transfer.service.common.EntityNotFoundReturner;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Реализация {@link PhoneTransferService}
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PhoneTransferServiceImpl implements PhoneTransferService {

    private final static String MESSAGE = "Не найден перевод по номеру телефона с ID ";

    private final PhoneTransferRepository repository;
    private final PhoneTransferMapper mapper;
    private final EntityNotFoundReturner notFoundReturner;

    /**
     * @param ids список технических идентификаторов {@link PhoneTransferEntity}
     * @return лист {@link PhoneTransferDto}
     */
    @Override
    public List<PhoneTransferDto> findAllById(List<Long> ids) {
        final List<PhoneTransferDto> phoneTransferDtoList = new ArrayList<>();

        for (Long id : ids) {
            phoneTransferDtoList.add(findById(id));
        }

        return phoneTransferDtoList;
    }

    /**
     * @param id технический идентификатор {@link PhoneTransferEntity}
     * @return {@link PhoneTransferDto}
     */
    @Override
    public PhoneTransferDto findById(Long id) {
        final PhoneTransferEntity phoneTransfer = repository.findById(id)
                .orElseThrow(() -> notFoundReturner.getEntityNotFoundException(id, MESSAGE));

        return mapper.toDto(phoneTransfer);
    }

    /**
     * @param phoneTransfer {@link PhoneTransferDto}
     * @return {@link PhoneTransferDto}
     */
    @Override
    @Transactional
    public PhoneTransferDto save(PhoneTransferDto phoneTransfer) {
        final PhoneTransferEntity transfer = repository.save(
                mapper.toEntity(phoneTransfer));

        return mapper.toDto(transfer);
    }

    /**
     * @param phoneTransfer {@link PhoneTransferDto}
     * @param id            технический идентификатор {@link PhoneTransferEntity}
     * @return {@link PhoneTransferDto}
     */
    @Override
    @Transactional
    public PhoneTransferDto update(Long id, PhoneTransferDto phoneTransfer) {
        final PhoneTransferEntity transfer = repository.findById(id)
                .orElseThrow(() -> notFoundReturner.getEntityNotFoundException(id, MESSAGE));

        final PhoneTransferEntity phoneTransferEntity = mapper.mergeToEntity(phoneTransfer, transfer);

        return mapper.toDto(repository.save(phoneTransferEntity));
    }
}
