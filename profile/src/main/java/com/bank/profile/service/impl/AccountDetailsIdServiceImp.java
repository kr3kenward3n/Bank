package com.bank.profile.service.impl;

import com.bank.profile.dto.AccountDetailsIdDto;
import com.bank.profile.entity.AccountDetailsIdEntity;
import com.bank.profile.mapper.AccountDetailsIdMapper;
import com.bank.profile.repository.AccountDetailsIdRepository;
import com.bank.profile.service.AccountDetailsIdService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Реализация для {@link AccountDetailsIdService}
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountDetailsIdServiceImp implements AccountDetailsIdService {

    AccountDetailsIdRepository repository;
    AccountDetailsIdMapper mapper;

    /**
     * @param id технический идентификатор для {@link AccountDetailsIdEntity}.
     * @return {@link AccountDetailsIdDto}.
     */
    @Override
    public AccountDetailsIdDto findById(Long id) {
        final AccountDetailsIdEntity accountDetailsId = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("accountDetailsId с данным id не найден!")
        );
        return mapper.toDto(accountDetailsId);
    }

    /**
     * @param accountDetailsIdDto {@link AccountDetailsIdDto}.
     * @return {@link AccountDetailsIdDto}.
     */
    @Override
    @Transactional
    public AccountDetailsIdDto save(AccountDetailsIdDto accountDetailsIdDto) {
        final AccountDetailsIdEntity accountDetailsId = repository.save(mapper.toEntity(accountDetailsIdDto));
        return mapper.toDto(accountDetailsId);
    }

    /**
     * @param id   технический идентификатор для {@link AccountDetailsIdEntity}.
     * @param accountDetailsIdDto {@link AccountDetailsIdDto}.
     * @return {@link AccountDetailsIdDto}.
     */
    @Override
    @Transactional
    public AccountDetailsIdDto update(Long id, AccountDetailsIdDto accountDetailsIdDto) {
        final AccountDetailsIdEntity accountDetailsById = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Обновление невозможно, accountDetailsId не найден!")
        );
        final AccountDetailsIdEntity accountDetailsId = repository.save(
                mapper.mergeToEntity(accountDetailsIdDto, accountDetailsById)
        );
        return mapper.toDto(accountDetailsId);
    }

    /**
     * @param ids список технических идентификаторов {@link AccountDetailsIdEntity}.
     * @return {@link List<AccountDetailsIdDto>}.
     */
    @Override
    public List<AccountDetailsIdDto> findAllById(List<Long> ids) {
        final List<AccountDetailsIdEntity> accountDetailsIdEntities = repository.findAllById(ids);


        return mapper.toDtoList(accountDetailsIdEntities);
    }
}
