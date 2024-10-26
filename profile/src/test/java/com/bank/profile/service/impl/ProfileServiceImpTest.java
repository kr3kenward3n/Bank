package com.bank.profile.service.impl;

import com.bank.profile.dto.ActualRegistrationDto;
import com.bank.profile.dto.PassportDto;
import com.bank.profile.dto.ProfileDto;
import com.bank.profile.entity.ActualRegistrationEntity;
import com.bank.profile.entity.PassportEntity;
import com.bank.profile.entity.ProfileEntity;
import com.bank.profile.mapper.ProfileMapper;
import com.bank.profile.repository.ProfileRepository;
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
 * Тесты для {@link ProfileServiceImp}
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Тесты для ProfileServiceImp")
class ProfileServiceImpTest {

    private final static Long testId = 1L;

    private ProfileEntity profileEntity;
    private ProfileDto profileDto;


    @Mock
    private ProfileRepository repository;

    @Mock
    private ProfileMapper mapper;

    @InjectMocks
    private ProfileServiceImp service;

    /**
     * Инициализация {@link ProfileEntity} and {@link ProfileDto}
     */
    @BeforeEach
    void initAndCreateEntity() {
        profileEntity = new ProfileEntity(1L, 1L, "hello", "hello",
                777L, 777L, new PassportEntity(), new ActualRegistrationEntity());

        profileDto = new ProfileDto(1L, 1L, "hello", "hello",
                777L, 777L, new PassportDto(), new ActualRegistrationDto());

    }


    @Test
    @DisplayName("Поиск по id, позитивный сценарий")
    void findByIdPositiveTest() {
        when(repository.findById(testId)).thenReturn(Optional.of(profileEntity));
        when(mapper.toDto(profileEntity)).thenReturn(profileDto);

        ProfileDto result = service.findById(testId);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(profileEntity.getId(), result.getId()),
                () -> assertEquals(profileEntity.getPhoneNumber(), result.getPhoneNumber()),
                () -> assertEquals(profileEntity.getEmail(), result.getEmail()),
                () -> assertEquals(profileEntity.getNameOnCard(), result.getNameOnCard()),
                () -> assertEquals(profileEntity.getInn(), result.getInn()),
                () -> assertEquals(profileEntity.getSnils(), result.getSnils())
        );
    }


    @Test
    @DisplayName("Поиск по несуществующему id, негативный сценарий")
    void findByNonExistIdNegativeTest() {
        when(repository.findById(testId)).thenReturn(Optional.empty());

        assertEquals("profile с данным id не найден!",
                assertThrows(EntityNotFoundException.class,
                        () -> service.findById(testId)).getMessage());
    }


    @Test
    @DisplayName("Сохранение по id, позитивный сценарий")
    void saveByIdPositiveTest() {
        when(repository.save(profileEntity)).thenReturn(profileEntity);
        when(mapper.toEntity(profileDto)).thenReturn(profileEntity);
        when(mapper.toDto(profileEntity)).thenReturn(profileDto);

        ProfileDto result = service.save(profileDto);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(profileDto.getId(), result.getId()),
                () -> assertEquals(profileDto.getPhoneNumber(), result.getPhoneNumber()),
                () -> assertEquals(profileDto.getEmail(), result.getEmail()),
                () -> assertEquals(profileDto.getNameOnCard(), result.getNameOnCard()),
                () -> assertEquals(profileDto.getInn(), result.getInn()),
                () -> assertEquals(profileDto.getSnils(), result.getSnils())
        );
    }


    @Test
    @DisplayName("Сохранение null, негативный сценарий")
    void saveNullNegativeTest() {
        when(repository.save(profileEntity)).thenReturn(null);
        when(mapper.toEntity(profileDto)).thenReturn(profileEntity);

        ProfileDto result = service.save(profileDto);

        assertNull(result);
    }


    @Test
    @DisplayName("Обновление по id, позитивный сценарий")
    void updateByIdPositiveTest() {
        profileDto.setSnils(777L);

        when(repository.findById(testId)).thenReturn(Optional.of(profileEntity));
        when(service.save(profileDto)).thenReturn(profileDto);
        when(mapper.mergeToEntity(profileDto, profileEntity)).thenReturn(profileEntity);

        ProfileDto result = service.update(testId, profileDto);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(profileDto.getId(), result.getId()),
                () -> assertEquals(profileDto.getPhoneNumber(), result.getPhoneNumber()),
                () -> assertEquals(profileDto.getEmail(), result.getEmail()),
                () -> assertEquals(profileDto.getNameOnCard(), result.getNameOnCard()),
                () -> assertEquals(profileDto.getInn(), result.getInn()),
                () -> assertEquals(profileDto.getSnils(), result.getSnils())
        );
    }


    @Test
    @DisplayName("Обновление по несуществующему id, негативный сценарий")
    void updateByNonExistIdNegativeTest() {
        when(repository.findById(testId)).thenReturn(Optional.empty());

        assertEquals("Обновление невозможно, profile не найден!",
                assertThrows(EntityNotFoundException.class,
                        () -> service.update(testId, profileDto)).getMessage());
    }


    @Test
    @DisplayName("Поиск по нескольким id, позитивный сценарий")
    void findAllByIdPositiveTest() {
        List<Long> longList = new ArrayList<>(List.of(testId, testId, testId));

        List<ProfileEntity> EntityList = new ArrayList<>(
                List.of(profileEntity, profileEntity, profileEntity));

        List<ProfileDto> DtoList = new ArrayList<>(
                List.of(profileDto, profileDto, profileDto));

        when(repository.findAllById(longList)).thenReturn(EntityList);
        when(mapper.toDtoList(EntityList)).thenReturn(DtoList);

        List<ProfileDto> result = service.findAllById(longList);

        assertNotNull(result);
        assertEquals(3, result.size());
    }


    @Test
    @DisplayName("Поиск по нескольким несуществующим id, негативный сценарий")
    void findAllByNonExistIdNegativeTest() {
        List<Long> longList = new ArrayList<>();

        when(repository.findAllById(longList)).thenReturn(new ArrayList<>());

        List<ProfileDto> result = service.findAllById(longList);

        assertNotNull(result);
        assertEquals(0, result.size());
    }

}