package com.bank.account.service;

import com.bank.account.dto.AccountDetailsDto;
import com.bank.account.entity.AccountDetailsEntity;
import com.bank.account.mapper.AccountDetailsMapper;
import com.bank.account.repository.AccountDetailsRepository;
import com.bank.account.service.common.ExceptionReturner;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Реализация {@link AccountDetailsService}
 */
@Service
@RequiredArgsConstructor
public class AccountDetailsServiceImpl implements AccountDetailsService {

    private static final String MESSAGE_PREFIX = "Не существующий id = ";
    private final AccountDetailsMapper mapper;
    private final AccountDetailsRepository repository;

    private final ExceptionReturner exceptionReturner;

    /**
     * @param id технический идентификатор {@link AccountDetailsEntity}
     * @return {@link AccountDetailsDto}
     */
    @Override
    public AccountDetailsDto findById(Long id) {
        return mapper.toDto(repository.findById(id)
                .orElseThrow(() -> exceptionReturner.getEntityNotFoundException(MESSAGE_PREFIX + id))
        );
    }

    /**
     * @param ids лист технических идентификаторов {@link AccountDetailsEntity}
     * @return {@link List<AccountDetailsDto>}
     */
    @Override
    public List<AccountDetailsDto> findAllById(List<Long> ids) {

        final List<AccountDetailsEntity> accountDetailsList = ids.stream()
                .map(id -> repository.findById(id)
                        .orElseThrow(() -> exceptionReturner.getEntityNotFoundException(MESSAGE_PREFIX + id)))
                .toList();
        return mapper.toDtoList(accountDetailsList);
    }

    /**
     * @param accountDetailsDto {@link AccountDetailsDto}
     * @return {@link AccountDetailsDto}
     */
    @Override
    @Transactional
    public AccountDetailsDto save(AccountDetailsDto accountDetailsDto) {

        final AccountDetailsEntity accountDetails = repository.save(
                mapper.toEntity(accountDetailsDto)
        );

        return mapper.toDto(accountDetails);
    }

    /**
     * @param id                технический идентификатор {@link AccountDetailsEntity}
     * @param accountDetailsDto {@link AccountDetailsDto}
     * @return {@link AccountDetailsDto}
     */
    @Override
    @Transactional
    public AccountDetailsDto update(Long id, AccountDetailsDto accountDetailsDto) {

        final AccountDetailsEntity accountDetails = repository.findById(id)
                .orElseThrow(() -> exceptionReturner.getEntityNotFoundException(MESSAGE_PREFIX + id));

        final AccountDetailsEntity updateAccountDetails = repository.save(
                mapper.mergeToEntity(accountDetails, accountDetailsDto)
        );

        return mapper.toDto(updateAccountDetails);
    }
}

