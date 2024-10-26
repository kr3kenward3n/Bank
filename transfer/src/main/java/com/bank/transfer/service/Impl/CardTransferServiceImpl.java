package com.bank.transfer.service.Impl;

import com.bank.transfer.dto.CardTransferDto;
import com.bank.transfer.entity.CardTransferEntity;
import com.bank.transfer.mapper.CardTransferMapper;
import com.bank.transfer.repository.CardTransferRepository;
import com.bank.transfer.service.common.EntityNotFoundReturner;
import com.bank.transfer.service.CardTransferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Реализация {@link CardTransferService}
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CardTransferServiceImpl implements CardTransferService {

    private final static String MESSAGE = "Не найден перевод по номеру карты с ID ";

    private final CardTransferRepository repository;
    private final CardTransferMapper mapper;
    private final EntityNotFoundReturner notFoundReturner;

    /**
     * @param ids список технических идентификаторов {@link CardTransferEntity}
     * @return лист {@link CardTransferDto}
     */
    @Override
    public List<CardTransferDto> findAllById(List<Long> ids) {
        final List<CardTransferDto> cardTransferDtoList = new ArrayList<>();

        for (Long id : ids) {
            cardTransferDtoList.add(findById(id));
        }

        return cardTransferDtoList;
    }

    /**
     * @param id технический идентификатор {@link CardTransferEntity}
     * @return {@link CardTransferDto}
     */
    @Override
    public CardTransferDto findById(Long id) {
        final CardTransferEntity cardTransfer = repository.findById(id)
                .orElseThrow(() -> notFoundReturner.getEntityNotFoundException(id, MESSAGE));

        return mapper.toDto(cardTransfer);
    }

    /**
     * @param cardTransfer {@link CardTransferDto}
     * @return {@link CardTransferDto}
     */
    @Override
    @Transactional
    public CardTransferDto save(CardTransferDto cardTransfer) {
        final CardTransferEntity transfer = repository.save(
                mapper.toEntity(cardTransfer));

        return mapper.toDto(transfer);
    }

    /**
     * @param cardTransfer {@link CardTransferDto}
     * @param id           технический идентификатор {@link CardTransferEntity}
     * @return {@link CardTransferDto}
     */
    @Override
    @Transactional
    public CardTransferDto update(Long id, CardTransferDto cardTransfer) {
        final CardTransferEntity transfer = repository.findById(id)
                .orElseThrow(() -> notFoundReturner.getEntityNotFoundException(id, MESSAGE));

        final CardTransferEntity cardTransferEntity = mapper.mergeToEntity(cardTransfer, transfer);

        return mapper.toDto(repository.save(cardTransferEntity));
    }
}
