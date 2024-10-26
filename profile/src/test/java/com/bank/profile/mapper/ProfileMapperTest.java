package com.bank.profile.mapper;

import com.bank.profile.dto.ActualRegistrationDto;
import com.bank.profile.dto.PassportDto;
import com.bank.profile.dto.ProfileDto;
import com.bank.profile.entity.ActualRegistrationEntity;
import com.bank.profile.entity.PassportEntity;
import com.bank.profile.entity.ProfileEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Тесты для {@link ProfileMapper}
 */
@DisplayName("Тесты для ProfileMapper")
class ProfileMapperTest {

    private ProfileDto dto;
    private ProfileEntity entity;

    private final ProfileMapper mapper = new ProfileMapperImpl();


    @BeforeEach
    void createEntityAndDto() {
        dto = new ProfileDto(1L, 1L, "hello", "hello",
                777L, 777L, new PassportDto(), new ActualRegistrationDto());

        entity = new ProfileEntity(1L, 1L, "hello", "hello",
                777L, 777L, new PassportEntity(), new ActualRegistrationEntity());
    }


    @Test
    @DisplayName("Маппинг в дто")
    void toDtoTest() {
        ProfileDto result = mapper.toDto(entity);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(entity.getId(), result.getId()),
                () -> assertEquals(entity.getPhoneNumber(), result.getPhoneNumber()),
                () -> assertEquals(entity.getEmail(), result.getEmail()),
                () -> assertEquals(entity.getNameOnCard(), result.getNameOnCard()),
                () -> assertEquals(entity.getInn(), result.getInn()),
                () -> assertEquals(entity.getSnils(), result.getSnils())
        );
    }


    @Test
    @DisplayName("Маппинг в дто, на вход передан null")
    void toDtoNullTest() {
        assertNull(mapper.toDto(null));
    }


    @Test
    @DisplayName("Маппинг в энтити")
    void toEntityTest() {
        ProfileEntity result = mapper.toEntity(dto);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(dto.getPhoneNumber(), result.getPhoneNumber()),
                () -> assertEquals(dto.getEmail(), result.getEmail()),
                () -> assertEquals(dto.getNameOnCard(), result.getNameOnCard()),
                () -> assertEquals(dto.getInn(), result.getInn()),
                () -> assertEquals(dto.getSnils(), result.getSnils())
        );
    }


    @Test
    @DisplayName("Маппинг в энтити, на вход передан null")
    void toEntityNullTest() {
        assertNull(mapper.toEntity(null));
    }


    @Test
    @DisplayName("Слияние в энтити")
    void mergeToEntityTest() {
        ProfileEntity result = mapper.mergeToEntity(dto, entity);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(entity.getId(), result.getId()),
                () -> assertEquals(entity.getPhoneNumber(), result.getPhoneNumber()),
                () -> assertEquals(entity.getEmail(), result.getEmail()),
                () -> assertEquals(entity.getNameOnCard(), result.getNameOnCard()),
                () -> assertEquals(entity.getInn(), result.getInn()),
                () -> assertEquals(entity.getSnils(), result.getSnils())
        );
    }


    @Test
    @DisplayName("Слияние в энтити, на вход передан null")
    void mergeToEntityNullTest() {
        assertNull(mapper.mergeToEntity(null, null));
    }


    @Test
    @DisplayName("Маппинг в лист дто")
    void toDtoListTest() {
        List<ProfileDto> result = mapper.toDtoList(new ArrayList<>(List.of(entity, entity, entity)));

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(entity.getId(), result.get(0).getId()),
                () -> assertEquals(entity.getId(), result.get(1).getId()),
                () -> assertEquals(entity.getId(), result.get(2).getId())
        );
    }


    @Test
    @DisplayName("Маппинг в пустой лист дто")
    void toDtoListEmptyListTest() {
        assertEquals(new ArrayList<>(), mapper.toDtoList(Collections.emptyList()));
    }


}