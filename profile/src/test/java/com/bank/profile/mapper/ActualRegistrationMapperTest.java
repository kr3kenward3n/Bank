package com.bank.profile.mapper;

import com.bank.profile.dto.ActualRegistrationDto;
import com.bank.profile.entity.ActualRegistrationEntity;
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
 * Тесты для {@link ActualRegistrationMapper}
 */
@DisplayName("Тесты для ActualRegistrationMapper")
class ActualRegistrationMapperTest {

    private ActualRegistrationDto dto;
    private ActualRegistrationEntity entity;

    private final ActualRegistrationMapper mapper = new ActualRegistrationMapperImpl();

    @BeforeEach
    void createEntityAndDto() {
        dto = new ActualRegistrationDto(1L, "hello", "hello", "hello", "hello",
                "hello", "hello", "hello", "hello", "hello", 1L);
        entity = new ActualRegistrationEntity(2L, "hello", "hello", "hello", "hello",
                "hello", "hello", "hello", "hello", "hello", 2L);
    }


    @Test
    @DisplayName("Маппинг в дто")
    void toDtoTest() {
        ActualRegistrationDto result = mapper.toDto(entity);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(entity.getId(), result.getId()),
                () -> assertEquals(entity.getCountry(), result.getCountry()),
                () -> assertEquals(entity.getRegion(), result.getRegion()),
                () -> assertEquals(entity.getCity(), result.getCity()),
                () -> assertEquals(entity.getDistrict(), result.getDistrict()),
                () -> assertEquals(entity.getLocality(), result.getLocality()),
                () -> assertEquals(entity.getStreet(), result.getStreet()),
                () -> assertEquals(entity.getHouseNumber(), result.getHouseNumber()),
                () -> assertEquals(entity.getHouseBlock(), result.getHouseBlock()),
                () -> assertEquals(entity.getFlatNumber(), result.getFlatNumber()),
                () -> assertEquals(entity.getIndex(), result.getIndex())
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
        ActualRegistrationEntity result = mapper.toEntity(dto);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(dto.getCountry(), result.getCountry()),
                () -> assertEquals(dto.getRegion(), result.getRegion()),
                () -> assertEquals(dto.getCity(), result.getCity()),
                () -> assertEquals(dto.getDistrict(), result.getDistrict()),
                () -> assertEquals(dto.getLocality(), result.getLocality()),
                () -> assertEquals(dto.getStreet(), result.getStreet()),
                () -> assertEquals(dto.getHouseNumber(), result.getHouseNumber()),
                () -> assertEquals(dto.getHouseBlock(), result.getHouseBlock()),
                () -> assertEquals(dto.getFlatNumber(), result.getFlatNumber()),
                () -> assertEquals(dto.getIndex(), result.getIndex())
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
        ActualRegistrationEntity result = mapper.mergeToEntity(dto, entity);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(entity.getId(), result.getId()),
                () -> assertEquals(entity.getCountry(), result.getCountry()),
                () -> assertEquals(entity.getRegion(), result.getRegion()),
                () -> assertEquals(entity.getCity(), result.getCity()),
                () -> assertEquals(entity.getDistrict(), result.getDistrict()),
                () -> assertEquals(entity.getLocality(), result.getLocality()),
                () -> assertEquals(entity.getStreet(), result.getStreet()),
                () -> assertEquals(entity.getHouseNumber(), result.getHouseNumber()),
                () -> assertEquals(entity.getHouseBlock(), result.getHouseBlock()),
                () -> assertEquals(entity.getFlatNumber(), result.getFlatNumber()),
                () -> assertEquals(entity.getIndex(), result.getIndex())
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
        List<ActualRegistrationDto> result = mapper.toDtoList(new ArrayList<>(List.of(entity, entity, entity)));

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