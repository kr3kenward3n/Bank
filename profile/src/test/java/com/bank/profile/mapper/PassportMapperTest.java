package com.bank.profile.mapper;

import com.bank.profile.dto.PassportDto;
import com.bank.profile.dto.RegistrationDto;
import com.bank.profile.entity.PassportEntity;
import com.bank.profile.entity.RegistrationEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Тесты для {@link PassportMapper}
 */
@DisplayName("Тесты для PassportMapper")
class PassportMapperTest {

    private PassportDto dto;
    private PassportEntity entity;

    private final PassportMapper mapper = new PassportMapperImpl();


    @BeforeEach
    void createEntityAndDto() {
        dto = new PassportDto(1L, 777, 777L, "hello", "hello",
                "hello", "hello", LocalDate.MIN, "hello", "hello",
                LocalDate.MIN, 777, LocalDate.MIN, new RegistrationDto());

        entity = new PassportEntity(1L, 777, 777L, "hello", "hello",
                "hello", "hello", LocalDate.MIN, "hello", "hello",
                LocalDate.MIN, 777, LocalDate.MIN, new RegistrationEntity());
    }


    @Test
    @DisplayName("Маппинг в дто")
    void toDtoTest() {
        PassportDto result = mapper.toDto(entity);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(entity.getId(), result.getId()),
                () -> assertEquals(entity.getSeries(), result.getSeries()),
                () -> assertEquals(entity.getNumber(), result.getNumber()),
                () -> assertEquals(entity.getLastName(), result.getLastName()),
                () -> assertEquals(entity.getFirstName(), result.getFirstName()),
                () -> assertEquals(entity.getMiddleName(), result.getMiddleName()),
                () -> assertEquals(entity.getGender(), result.getGender()),
                () -> assertEquals(entity.getBirthDate(), result.getBirthDate()),
                () -> assertEquals(entity.getBirthPlace(), result.getBirthPlace()),
                () -> assertEquals(entity.getDivisionCode(), result.getDivisionCode()),
                () -> assertEquals(entity.getIssuedBy(), result.getIssuedBy())
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
        PassportEntity result = mapper.toEntity(dto);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(dto.getSeries(), result.getSeries()),
                () -> assertEquals(dto.getNumber(), result.getNumber()),
                () -> assertEquals(dto.getLastName(), result.getLastName()),
                () -> assertEquals(dto.getFirstName(), result.getFirstName()),
                () -> assertEquals(dto.getMiddleName(), result.getMiddleName()),
                () -> assertEquals(dto.getGender(), result.getGender()),
                () -> assertEquals(dto.getBirthDate(), result.getBirthDate()),
                () -> assertEquals(dto.getBirthPlace(), result.getBirthPlace()),
                () -> assertEquals(dto.getDivisionCode(), result.getDivisionCode()),
                () -> assertEquals(dto.getIssuedBy(), result.getIssuedBy())
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
        PassportEntity result = mapper.mergeToEntity(dto, entity);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(entity.getId(), result.getId()),
                () -> assertEquals(entity.getSeries(), result.getSeries()),
                () -> assertEquals(entity.getNumber(), result.getNumber()),
                () -> assertEquals(entity.getLastName(), result.getLastName()),
                () -> assertEquals(entity.getFirstName(), result.getFirstName()),
                () -> assertEquals(entity.getMiddleName(), result.getMiddleName()),
                () -> assertEquals(entity.getGender(), result.getGender()),
                () -> assertEquals(entity.getBirthDate(), result.getBirthDate()),
                () -> assertEquals(entity.getBirthPlace(), result.getBirthPlace()),
                () -> assertEquals(entity.getDivisionCode(), result.getDivisionCode()),
                () -> assertEquals(entity.getIssuedBy(), result.getIssuedBy())
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
        List<PassportDto> result = mapper.toDtoList(new ArrayList<>(List.of(entity, entity, entity)));

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