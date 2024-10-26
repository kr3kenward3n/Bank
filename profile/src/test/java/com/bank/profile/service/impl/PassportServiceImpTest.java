package com.bank.profile.service.impl;


import com.bank.profile.dto.PassportDto;
import com.bank.profile.dto.RegistrationDto;
import com.bank.profile.entity.PassportEntity;
import com.bank.profile.entity.RegistrationEntity;
import com.bank.profile.mapper.PassportMapper;
import com.bank.profile.repository.PassportRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
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
 * Тесты для {@link PassportServiceImp}
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Тесты для PassportServiceImp")
class PassportServiceImpTest {

    private final static Long testId = 1L;

    private PassportEntity passportEntity;
    private PassportDto passportDto;

    @Mock
    private PassportRepository repository;

    @Mock
    private PassportMapper mapper;

    @InjectMocks
    private PassportServiceImp service;

    /**
     * Инициализация {@link PassportEntity} and {@link PassportDto}
     */
    @BeforeEach
    void initAndCreateEntity() {
        passportEntity = new PassportEntity(1L, 777, 777L, "hello", "hello",
                "hello", "hello", LocalDate.MIN, "hello", "hello",
                LocalDate.MIN, 777, LocalDate.MIN, new RegistrationEntity());

        passportDto = new PassportDto(1L, 777, 777L, "hello", "hello",
                "hello", "hello", LocalDate.MIN, "hello", "hello",
                LocalDate.MIN, 777, LocalDate.MIN, new RegistrationDto());
    }


    @Test
    @DisplayName("Поиск по id, позитивный сценарий")
    void findByIdPositiveTest() {
        when(repository.findById(testId)).thenReturn(Optional.of(passportEntity));
        when(mapper.toDto(passportEntity)).thenReturn(passportDto);

        PassportDto result = service.findById(testId);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(passportEntity.getId(), result.getId()),
                () -> assertEquals(passportEntity.getSeries(), result.getSeries()),
                () -> assertEquals(passportEntity.getNumber(), result.getNumber()),
                () -> assertEquals(passportEntity.getLastName(), result.getLastName()),
                () -> assertEquals(passportEntity.getFirstName(), result.getFirstName()),
                () -> assertEquals(passportEntity.getMiddleName(), result.getMiddleName()),
                () -> assertEquals(passportEntity.getGender(), result.getGender()),
                () -> assertEquals(passportEntity.getBirthDate(), result.getBirthDate()),
                () -> assertEquals(passportEntity.getBirthPlace(), result.getBirthPlace()),
                () -> assertEquals(passportEntity.getDivisionCode(), result.getDivisionCode()),
                () -> assertEquals(passportEntity.getIssuedBy(), result.getIssuedBy())
        );
    }


    @Test
    @DisplayName("Поиск по несуществующему id, негативный сценарий")
    void findByNonExistIdNegativeTest() {
        when(repository.findById(testId)).thenReturn(Optional.empty());

        assertEquals("passport с данным id не найден!",
                assertThrows(EntityNotFoundException.class,
                        () -> service.findById(testId)).getMessage());
    }


    @Test
    @DisplayName("Сохранение по id, позитивный сценарий")
    void saveByIdPositiveTest() {
        when(repository.save(passportEntity)).thenReturn(passportEntity);
        when(mapper.toEntity(passportDto)).thenReturn(passportEntity);
        when(mapper.toDto(passportEntity)).thenReturn(passportDto);

        PassportDto result = service.save(passportDto);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(passportDto.getId(), result.getId()),
                () -> assertEquals(passportDto.getSeries(), result.getSeries()),
                () -> assertEquals(passportDto.getNumber(), result.getNumber()),
                () -> assertEquals(passportDto.getLastName(), result.getLastName()),
                () -> assertEquals(passportDto.getFirstName(), result.getFirstName()),
                () -> assertEquals(passportDto.getMiddleName(), result.getMiddleName()),
                () -> assertEquals(passportDto.getGender(), result.getGender()),
                () -> assertEquals(passportDto.getBirthDate(), result.getBirthDate()),
                () -> assertEquals(passportDto.getBirthPlace(), result.getBirthPlace()),
                () -> assertEquals(passportDto.getDivisionCode(), result.getDivisionCode()),
                () -> assertEquals(passportDto.getIssuedBy(), result.getIssuedBy())
        );
    }


    @Test
    @DisplayName("Сохранение null, негативный сценарий")
    void saveNullNegativeTest() {
        when(repository.save(passportEntity)).thenReturn(null);
        when(mapper.toEntity(passportDto)).thenReturn(passportEntity);

        PassportDto result = service.save(passportDto);

        assertNull(result);
    }


    @Test
    @DisplayName("Обновление по id, позитивный сценарий")
    void updateByIdPositiveTest() {
        passportDto.setGender("man");

        when(repository.findById(testId)).thenReturn(Optional.of(passportEntity));
        when(service.save(passportDto)).thenReturn(passportDto);
        when(mapper.mergeToEntity(passportDto, passportEntity)).thenReturn(passportEntity);

        PassportDto result = service.update(testId, passportDto);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(passportDto.getId(), result.getId()),
                () -> assertEquals(passportDto.getSeries(), result.getSeries()),
                () -> assertEquals(passportDto.getNumber(), result.getNumber()),
                () -> assertEquals(passportDto.getLastName(), result.getLastName()),
                () -> assertEquals(passportDto.getFirstName(), result.getFirstName()),
                () -> assertEquals(passportDto.getMiddleName(), result.getMiddleName()),
                () -> assertEquals(passportDto.getGender(), result.getGender()),
                () -> assertEquals(passportDto.getBirthDate(), result.getBirthDate()),
                () -> assertEquals(passportDto.getBirthPlace(), result.getBirthPlace()),
                () -> assertEquals(passportDto.getDivisionCode(), result.getDivisionCode()),
                () -> assertEquals(passportDto.getIssuedBy(), result.getIssuedBy())
        );
    }


    @Test
    @DisplayName("Обновление по несуществующему id, негативный сценарий")
    void updateByNonExistIdNegativeTest() {
        when(repository.findById(testId)).thenReturn(Optional.empty());

        assertEquals("Обновление невозможно, passport не найден!",
                assertThrows(EntityNotFoundException.class,
                        () -> service.update(testId, passportDto)).getMessage());
    }


    @Test
    @DisplayName("Поиск по нескольким id, позитивный сценарий")
    void findAllByIdPositiveTest() {
        List<Long> longList = new ArrayList<>(List.of(testId, testId, testId));

        List<PassportEntity> EntityList = new ArrayList<>(
                List.of(passportEntity, passportEntity, passportEntity));

        List<PassportDto> DtoList = new ArrayList<>(
                List.of(passportDto, passportDto, passportDto));

        when(repository.findAllById(longList)).thenReturn(EntityList);
        when(mapper.toDtoList(EntityList)).thenReturn(DtoList);

        List<PassportDto> result = service.findAllById(longList);

        assertNotNull(result);
        assertEquals(3, result.size());
    }


    @Test
    @DisplayName("Поиск по нескольким несуществующим id, негативный сценарий")
    void findAllByNonExistIdNegativeTest() {
        List<Long> longList = new ArrayList<>();

        when(repository.findAllById(longList)).thenReturn(new ArrayList<>());

        List<PassportDto> result = service.findAllById(longList);

        assertNotNull(result);
        assertEquals(0, result.size());
    }

}
