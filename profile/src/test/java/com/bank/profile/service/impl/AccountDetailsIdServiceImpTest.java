package com.bank.profile.service.impl;

import com.bank.profile.dto.AccountDetailsIdDto;
import com.bank.profile.dto.ProfileDto;
import com.bank.profile.entity.AccountDetailsIdEntity;
import com.bank.profile.entity.ProfileEntity;
import com.bank.profile.mapper.AccountDetailsIdMapper;
import com.bank.profile.repository.AccountDetailsIdRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

/**
 * Тесты для {@link AccountDetailsIdServiceImp}
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Тесты для AccountDetailsIdServiceImp")
class AccountDetailsIdServiceImpTest {

    private final static Long testId = 1L;

    private AccountDetailsIdEntity accountDetailsIdEntity;
    private AccountDetailsIdDto accountDetailsIdDto;

    @Mock
    private AccountDetailsIdRepository repository;

    @Mock
    private AccountDetailsIdMapper mapper;

    @InjectMocks
    private AccountDetailsIdServiceImp service;

    /**
     * Инициализация {@link AccountDetailsIdEntity} and {@link AccountDetailsIdDto}
     */
    @BeforeEach
    void initAndCreateEntity() {
        accountDetailsIdEntity = new AccountDetailsIdEntity(1L, 1L, new ProfileEntity());
        accountDetailsIdDto = new AccountDetailsIdDto(1L, 1L, new ProfileDto());
    }


    @Test
    @DisplayName("Поиск по id, позитивный сценарий")
    void findByIdPositiveTest() {
        when(repository.findById(testId)).thenReturn(Optional.of(accountDetailsIdEntity));
        when(mapper.toDto(accountDetailsIdEntity)).thenReturn(accountDetailsIdDto);

        AccountDetailsIdDto result = service.findById(testId);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(accountDetailsIdEntity.getId(), result.getId()),
                () -> assertEquals(accountDetailsIdEntity.getAccountId(), result.getAccountId())
        );
    }


    @Test
    @DisplayName("Поиск по несуществующему id, негативный сценарий")
    void findByNonExistIdNegativeTest() {
        when(repository.findById(testId)).thenReturn(Optional.empty());

        assertEquals("accountDetailsId с данным id не найден!",
                assertThrows(EntityNotFoundException.class,
                        () -> service.findById(testId)).getMessage());
    }


    @Test
    @DisplayName("Сохранение по id, позитивный сценарий")
    void saveByIdPositiveTest() {
        when(repository.save(accountDetailsIdEntity)).thenReturn(accountDetailsIdEntity);
        when(mapper.toEntity(accountDetailsIdDto)).thenReturn(accountDetailsIdEntity);
        when(mapper.toDto(accountDetailsIdEntity)).thenReturn(accountDetailsIdDto);

        AccountDetailsIdDto result = service.save(accountDetailsIdDto);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(accountDetailsIdDto.getId(), result.getId()),
                () -> assertEquals(accountDetailsIdDto.getAccountId(), result.getAccountId())
        );
    }


    @Test
    @DisplayName("Сохранение null, негативный сценарий")
    void saveNullNegativeTest() {
        when(repository.save(accountDetailsIdEntity)).thenReturn(null);
        when(mapper.toEntity(accountDetailsIdDto)).thenReturn(accountDetailsIdEntity);

        AccountDetailsIdDto result = service.save(accountDetailsIdDto);

        assertNull(result);
    }


    @Test
    @DisplayName("Обновление по id, позитивный сценарий")
    void updateByIdPositiveTest() {
        accountDetailsIdDto.setAccountId(2L);

        when(repository.findById(testId)).thenReturn(Optional.of(accountDetailsIdEntity));
        when(service.save(accountDetailsIdDto)).thenReturn(accountDetailsIdDto);
        when(mapper.mergeToEntity(accountDetailsIdDto, accountDetailsIdEntity)).thenReturn(accountDetailsIdEntity);

        AccountDetailsIdDto result = service.update(testId, accountDetailsIdDto);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(accountDetailsIdDto.getId(), result.getId()),
                () -> assertEquals(accountDetailsIdDto.getAccountId(), result.getAccountId())
        );
    }


    @Test
    @DisplayName("Обновление по несуществующему id, негативный сценарий")
    void updateByNonExistIdNegativeTest() {
        when(repository.findById(testId)).thenReturn(Optional.empty());

        assertEquals("Обновление невозможно, accountDetailsId не найден!",
                assertThrows(EntityNotFoundException.class,
                        () -> service.update(testId, accountDetailsIdDto)).getMessage());
    }


    @Test
    @DisplayName("Поиск по нескольким id, позитивный сценарий")
    void findAllByIdPositiveTest() {
        List<Long> longList = new ArrayList<>(List.of(testId, testId, testId));

        List<AccountDetailsIdEntity> detailsIdEntityList = new ArrayList<>(
                List.of(accountDetailsIdEntity, accountDetailsIdEntity, accountDetailsIdEntity));

        List<AccountDetailsIdDto> detailsIdDtoList = new ArrayList<>(
                List.of(accountDetailsIdDto, accountDetailsIdDto, accountDetailsIdDto));

        when(repository.findAllById(longList)).thenReturn(detailsIdEntityList);
        when(mapper.toDtoList(detailsIdEntityList)).thenReturn(detailsIdDtoList);

        List<AccountDetailsIdDto> result = service.findAllById(longList);

        assertNotNull(result);
        assertEquals(3, result.size());
    }


    @Test
    @DisplayName("Поиск по нескольким несуществующим id, негативный сценарий")
    void findAllByNonExistIdNegativeTest() {
        List<Long> longList = new ArrayList<>();

        when(repository.findAllById(longList)).thenReturn(new ArrayList<>());

        List<AccountDetailsIdDto> result = service.findAllById(longList);

        assertNotNull(result);
        assertEquals(0, result.size());
    }

}