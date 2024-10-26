package com.bank.transfer.service.Impl;

import com.bank.transfer.dto.AccountTransferDto;
import com.bank.transfer.entity.AccountTransferEntity;
import com.bank.transfer.mapper.AccountTransferMapper;
import com.bank.transfer.repository.AccountTransferRepository;
import com.bank.transfer.service.common.EntityNotFoundReturner;
import com.bank.transfer.service.AccountTransferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Реализация {@link AccountTransferService}
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AccountTransferServiceImpl implements AccountTransferService {

    private final static String MESSAGE = "Не найден перевод по номеру счета с ID ";

    private final AccountTransferRepository repository;
    private final AccountTransferMapper mapper;
    private final EntityNotFoundReturner notFoundReturner;

    /**
     * @param ids список технических идентификаторов {@link AccountTransferEntity}
     * @return лист {@link AccountTransferDto}
     */
    @Override
    public List<AccountTransferDto> findAllById(List<Long> ids) {
        final List<AccountTransferDto> accountTransferDtoList = new ArrayList<>();

        for (Long id : ids) {
            accountTransferDtoList.add(findById(id));
        }

        return accountTransferDtoList;
    }

    /**
     * @param id технический идентификатор {@link AccountTransferEntity}
     * @return {@link AccountTransferDto}
     */
    @Override
    public AccountTransferDto findById(Long id) {
        final AccountTransferEntity accountTransfer = repository.findById(id)
                .orElseThrow(() -> notFoundReturner.getEntityNotFoundException(id, MESSAGE));

        return mapper.toDto(accountTransfer);
    }

    /**
     * @param accountTransfer {@link AccountTransferDto}
     * @return {@link AccountTransferDto}
     */
    @Override
    @Transactional
    public AccountTransferDto save(AccountTransferDto accountTransfer) {
        final AccountTransferEntity transfer = repository.save(
                mapper.toEntity(accountTransfer));

        return mapper.toDto(transfer);
    }

    /**
     * @param accountTransfer {@link AccountTransferDto}
     * @param id              технический идентификатор {@link AccountTransferEntity}
     * @return {@link AccountTransferDto}
     */
    @Override
    @Transactional
    public AccountTransferDto update(Long id, AccountTransferDto accountTransfer) {
        final AccountTransferEntity transfer = repository.findById(id)
                .orElseThrow(() -> notFoundReturner.getEntityNotFoundException(id, MESSAGE));

        final AccountTransferEntity accountTransferEntity = mapper.mergeToEntity(accountTransfer, transfer);

        return mapper.toDto(repository.save(accountTransferEntity));
    }
}
