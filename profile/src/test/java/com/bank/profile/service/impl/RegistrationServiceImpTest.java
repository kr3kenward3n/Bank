package com.bank.profile.service.impl;

import com.bank.profile.dto.RegistrationDto;
import com.bank.profile.entity.RegistrationEntity;
import com.bank.profile.mapper.RegistrationMapper;
import com.bank.profile.repository.RegistrationRepository;
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
 * Тесты для {@link RegistrationServiceImp}
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Тесты для RegistrationServiceImp")
class RegistrationServiceImpTest {

    private final static Long testId = 1L;

    private RegistrationEntity registrationEntity;
    private RegistrationDto registrationDto;


    @Mock
    private RegistrationRepository repository;

    @Mock
    private RegistrationMapper mapper;

    @InjectMocks
    private RegistrationServiceImp service;

    /**
     * Инициализация {@link RegistrationEntity} and {@link RegistrationDto}
     */
    @BeforeEach
    void initAndCreateEntity() {
        registrationEntity = new RegistrationEntity(1L, "hello", "hello", "hello", "hello",
                "hello", "hello", "hello", "hello", "hello", 1L);

        registrationDto = new RegistrationDto(1L, "hello", "hello", "hello", "hello",
                "hello", "hello", "hello", "hello", "hello", 1L);

        registrationDto.setId(testId);
        registrationEntity.setId(testId);
    }


    @Test
    @DisplayName("Поиск по id, позитивный сценарий")
    void findByIdPositiveTest() {
        when(repository.findById(testId)).thenReturn(Optional.of(registrationEntity));
        when(mapper.toDto(registrationEntity)).thenReturn(registrationDto);

        RegistrationDto result = service.findById(testId);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(registrationEntity.getId(), result.getId()),
                () -> assertEquals(registrationEntity.getCountry(), result.getCountry()),
                () -> assertEquals(registrationEntity.getRegion(), result.getRegion()),
                () -> assertEquals(registrationEntity.getCity(), result.getCity()),
                () -> assertEquals(registrationEntity.getDistrict(), result.getDistrict()),
                () -> assertEquals(registrationEntity.getLocality(), result.getLocality()),
                () -> assertEquals(registrationEntity.getStreet(), result.getStreet()),
                () -> assertEquals(registrationEntity.getHouseNumber(), result.getHouseNumber()),
                () -> assertEquals(registrationEntity.getHouseBlock(), result.getHouseBlock()),
                () -> assertEquals(registrationEntity.getFlatNumber(), result.getFlatNumber()),
                () -> assertEquals(registrationEntity.getIndex(), result.getIndex())
        );
    }


    @Test
    @DisplayName("Поиск по несуществующему id, негативный сценарий")
    void findByNonExistIdNegativeTest() {
        when(repository.findById(testId)).thenReturn(Optional.empty());

        assertEquals("registration с данным id не найден!",
                assertThrows(EntityNotFoundException.class,
                        () -> service.findById(testId)).getMessage());
    }


    @Test
    @DisplayName("Сохранение по id, позитивный сценарий")
    void saveByIdPositiveTest() {
        when(repository.save(registrationEntity)).thenReturn(registrationEntity);
        when(mapper.toEntity(registrationDto)).thenReturn(registrationEntity);
        when(mapper.toDto(registrationEntity)).thenReturn(registrationDto);

        RegistrationDto result = service.save(registrationDto);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(registrationDto.getId(), result.getId()),
                () -> assertEquals(registrationDto.getCountry(), result.getCountry()),
                () -> assertEquals(registrationDto.getRegion(), result.getRegion()),
                () -> assertEquals(registrationDto.getCity(), result.getCity()),
                () -> assertEquals(registrationDto.getDistrict(), result.getDistrict()),
                () -> assertEquals(registrationDto.getLocality(), result.getLocality()),
                () -> assertEquals(registrationDto.getStreet(), result.getStreet()),
                () -> assertEquals(registrationDto.getHouseNumber(), result.getHouseNumber()),
                () -> assertEquals(registrationDto.getHouseBlock(), result.getHouseBlock()),
                () -> assertEquals(registrationDto.getFlatNumber(), result.getFlatNumber()),
                () -> assertEquals(registrationDto.getIndex(), result.getIndex())
        );
    }


    @Test
    @DisplayName("Сохранение null, негативный сценарий")
    void saveNullNegativeTest() {
        when(repository.save(registrationEntity)).thenReturn(null);
        when(mapper.toEntity(registrationDto)).thenReturn(registrationEntity);

        RegistrationDto result = service.save(registrationDto);

        assertNull(result);
    }


    @Test
    @DisplayName("Обновление по id, позитивный сценарий")
    void updateByIdPositiveTest() {
        registrationDto.setCity("Moskva");

        when(repository.findById(testId)).thenReturn(Optional.of(registrationEntity));
        when(service.save(registrationDto)).thenReturn(registrationDto);
        when(mapper.mergeToEntity(registrationDto, registrationEntity)).thenReturn(registrationEntity);

        RegistrationDto result = service.update(testId, registrationDto);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(registrationDto.getId(), result.getId()),
                () -> assertEquals(registrationDto.getCountry(), result.getCountry()),
                () -> assertEquals(registrationDto.getRegion(), result.getRegion()),
                () -> assertEquals(registrationDto.getCity(), result.getCity()),
                () -> assertEquals(registrationDto.getDistrict(), result.getDistrict()),
                () -> assertEquals(registrationDto.getLocality(), result.getLocality()),
                () -> assertEquals(registrationDto.getStreet(), result.getStreet()),
                () -> assertEquals(registrationDto.getHouseNumber(), result.getHouseNumber()),
                () -> assertEquals(registrationDto.getHouseBlock(), result.getHouseBlock()),
                () -> assertEquals(registrationDto.getFlatNumber(), result.getFlatNumber()),
                () -> assertEquals(registrationDto.getIndex(), result.getIndex())
        );
    }


    @Test
    @DisplayName("Обновление по несуществующему id, негативный сценарий")
    void updateByNonExistIdNegativeTest() {
        when(repository.findById(testId)).thenReturn(Optional.empty());

        assertEquals("Обновление невозможно, registration не найден!",
                assertThrows(EntityNotFoundException.class,
                        () -> service.update(testId, registrationDto)).getMessage());
    }


    @Test
    @DisplayName("Поиск по нескольким id, позитивный сценарий")
    void findAllByIdPositiveTest() {
        List<Long> longList = new ArrayList<>(List.of(testId, testId, testId));

        List<RegistrationEntity> EntityList = new ArrayList<>(
                List.of(registrationEntity, registrationEntity, registrationEntity));

        List<RegistrationDto> DtoList = new ArrayList<>(
                List.of(registrationDto, registrationDto, registrationDto));

        when(repository.findAllById(longList)).thenReturn(EntityList);
        when(mapper.toDtoList(EntityList)).thenReturn(DtoList);

        List<RegistrationDto> result = service.findAllById(longList);

        assertNotNull(result);
        assertEquals(3, result.size());
    }


    @Test
    @DisplayName("Поиск по нескольким несуществующим id, негативный сценарий")
    void findAllByNonExistIdNegativeTest() {
        List<Long> longList = new ArrayList<>();

        when(repository.findAllById(longList)).thenReturn(new ArrayList<>());

        List<RegistrationDto> result = service.findAllById(longList);

        assertNotNull(result);
        assertEquals(0, result.size());
    }

}