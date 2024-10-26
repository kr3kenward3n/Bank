package com.bank.profile.service.impl;

import com.bank.profile.dto.ActualRegistrationDto;
import com.bank.profile.entity.ActualRegistrationEntity;
import com.bank.profile.mapper.ActualRegistrationMapper;
import com.bank.profile.repository.ActualRegistrationRepository;
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
 * Тесты для {@link ActualRegistrationServiceImp}
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Тесты для ActualRegistrationServiceImp")
class ActualRegistrationServiceImpTest {

    private final static Long testId = 1L;

    private ActualRegistrationEntity actualRegistrationEntity;
    private ActualRegistrationDto actualRegistrationDto;

    @Mock
    private ActualRegistrationRepository repository;

    @Mock
    private ActualRegistrationMapper mapper;

    @InjectMocks
    private ActualRegistrationServiceImp service;

    /**
     * Инициализация {@link ActualRegistrationEntity} and {@link ActualRegistrationDto}
     */
    @BeforeEach
    void initAndCreateEntity() {
        actualRegistrationEntity = new ActualRegistrationEntity(1L, "hello", "hello",
                "hello", "hello", "hello", "hello", "hello",
                "hello", "hello", 1L);

        actualRegistrationDto = new ActualRegistrationDto(1L, "hello", "hello",
                "hello", "hello", "hello", "hello", "hello",
                "hello", "hello", 1L);
    }


    @Test
    @DisplayName("Поиск по id, позитивный сценарий")
    void findByIdPositiveTest() {
        when(repository.findById(testId)).thenReturn(Optional.of(actualRegistrationEntity));
        when(mapper.toDto(actualRegistrationEntity)).thenReturn(actualRegistrationDto);

        ActualRegistrationDto result = service.findById(testId);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(actualRegistrationEntity.getId(), result.getId()),
                () -> assertEquals(actualRegistrationEntity.getCountry(), result.getCountry()),
                () -> assertEquals(actualRegistrationEntity.getRegion(), result.getRegion()),
                () -> assertEquals(actualRegistrationEntity.getCity(), result.getCity()),
                () -> assertEquals(actualRegistrationEntity.getDistrict(), result.getDistrict()),
                () -> assertEquals(actualRegistrationEntity.getLocality(), result.getLocality()),
                () -> assertEquals(actualRegistrationEntity.getStreet(), result.getStreet()),
                () -> assertEquals(actualRegistrationEntity.getHouseNumber(), result.getHouseNumber()),
                () -> assertEquals(actualRegistrationEntity.getHouseBlock(), result.getHouseBlock()),
                () -> assertEquals(actualRegistrationEntity.getFlatNumber(), result.getFlatNumber()),
                () -> assertEquals(actualRegistrationEntity.getIndex(), result.getIndex())
        );
    }


    @Test
    @DisplayName("Поиск по несуществующему id, негативный сценарий")
    void findByNonExistIdNegativeTest() {
        when(repository.findById(testId)).thenReturn(Optional.empty());

        assertEquals("actualRegistration с данным id не найден!",
                assertThrows(EntityNotFoundException.class,
                        () -> service.findById(testId)).getMessage());
    }


    @Test
    @DisplayName("Сохранение по id, позитивный сценарий")
    void saveByIdPositiveTest() {
        when(repository.save(actualRegistrationEntity)).thenReturn(actualRegistrationEntity);
        when(mapper.toEntity(actualRegistrationDto)).thenReturn(actualRegistrationEntity);
        when(mapper.toDto(actualRegistrationEntity)).thenReturn(actualRegistrationDto);

        ActualRegistrationDto result = service.save(actualRegistrationDto);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(actualRegistrationDto.getId(), result.getId()),
                () -> assertEquals(actualRegistrationDto.getCountry(), result.getCountry()),
                () -> assertEquals(actualRegistrationDto.getRegion(), result.getRegion()),
                () -> assertEquals(actualRegistrationDto.getCity(), result.getCity()),
                () -> assertEquals(actualRegistrationDto.getDistrict(), result.getDistrict()),
                () -> assertEquals(actualRegistrationDto.getLocality(), result.getLocality()),
                () -> assertEquals(actualRegistrationDto.getStreet(), result.getStreet()),
                () -> assertEquals(actualRegistrationDto.getHouseNumber(), result.getHouseNumber()),
                () -> assertEquals(actualRegistrationDto.getHouseBlock(), result.getHouseBlock()),
                () -> assertEquals(actualRegistrationDto.getFlatNumber(), result.getFlatNumber()),
                () -> assertEquals(actualRegistrationDto.getIndex(), result.getIndex())
        );
    }


    @Test
    @DisplayName("Сохранение null, негативный сценарий")
    void saveNullNegativeTest() {
        when(repository.save(actualRegistrationEntity)).thenReturn(null);
        when(mapper.toEntity(actualRegistrationDto)).thenReturn(actualRegistrationEntity);

        ActualRegistrationDto result = service.save(actualRegistrationDto);

        assertNull(result);
    }


    @Test
    @DisplayName("Обновление по id, позитивный сценарий")
    void updateByIdPositiveTest() {
        actualRegistrationDto.setCity("Kazan");

        when(repository.findById(testId)).thenReturn(Optional.of(actualRegistrationEntity));
        when(service.save(actualRegistrationDto)).thenReturn(actualRegistrationDto);
        when(mapper.mergeToEntity(actualRegistrationDto, actualRegistrationEntity))
                .thenReturn(actualRegistrationEntity);

        ActualRegistrationDto result = service.update(testId, actualRegistrationDto);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(actualRegistrationDto.getId(), result.getId()),
                () -> assertEquals(actualRegistrationDto.getCountry(), result.getCountry()),
                () -> assertEquals(actualRegistrationDto.getRegion(), result.getRegion()),
                () -> assertEquals(actualRegistrationDto.getCity(), result.getCity()),
                () -> assertEquals(actualRegistrationDto.getDistrict(), result.getDistrict()),
                () -> assertEquals(actualRegistrationDto.getLocality(), result.getLocality()),
                () -> assertEquals(actualRegistrationDto.getStreet(), result.getStreet()),
                () -> assertEquals(actualRegistrationDto.getHouseNumber(), result.getHouseNumber()),
                () -> assertEquals(actualRegistrationDto.getHouseBlock(), result.getHouseBlock()),
                () -> assertEquals(actualRegistrationDto.getFlatNumber(), result.getFlatNumber()),
                () -> assertEquals(actualRegistrationDto.getIndex(), result.getIndex())
        );
    }


    @Test
    @DisplayName("Обновление по несуществующему id, негативный сценарий")
    void updateByNonExistIdNegativeTest() {
        when(repository.findById(testId)).thenReturn(Optional.empty());

        assertEquals("Обновление невозможно, ActualRegistration не найден!",
                assertThrows(EntityNotFoundException.class,
                        () -> service.update(testId, actualRegistrationDto)).getMessage());
    }


    @Test
    @DisplayName("Поиск по нескольким id, позитивный сценарий")
    void findAllByIdPositiveTest() {
        List<Long> longList = new ArrayList<>(List.of(testId, testId, testId));

        List<ActualRegistrationEntity> EntityList = new ArrayList<>(
                List.of(actualRegistrationEntity, actualRegistrationEntity, actualRegistrationEntity));

        List<ActualRegistrationDto> DtoList = new ArrayList<>(
                List.of(actualRegistrationDto, actualRegistrationDto, actualRegistrationDto));

        when(repository.findAllById(longList)).thenReturn(EntityList);
        when(mapper.toDtoList(EntityList)).thenReturn(DtoList);

        List<ActualRegistrationDto> result = service.findAllById(longList);

        assertNotNull(result);
        assertEquals(3, result.size());

    }


    @Test
    @DisplayName("Поиск по нескольким несуществующим id, негативный сценарий")
    void findAllByNonExistIdNegativeTest() {
        List<Long> longList = new ArrayList<>();

        when(repository.findAllById(longList)).thenReturn(new ArrayList<>());

        List<ActualRegistrationDto> result = service.findAllById(longList);

        assertNotNull(result);
        assertEquals(0, result.size());
    }

}

