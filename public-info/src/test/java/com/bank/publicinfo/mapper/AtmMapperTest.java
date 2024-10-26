package com.bank.publicinfo.mapper;

import com.bank.publicinfo.dto.AtmDto;
import com.bank.publicinfo.entity.AtmEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тесты для {@link AtmMapper}
 */
class AtmMapperTest {
    private AtmMapper mapper = new AtmMapperImpl();

    @Test
    @DisplayName("Маппинг в энтити")
    void toEntityTest() {
        final AtmDto atmDto =
                new AtmDto(0L, "address", LocalTime.MIN, LocalTime.MAX, Boolean.TRUE, null);

        AtmEntity atmEntity = mapper.toEntity(atmDto);

        assertAll(
                () -> assertNotNull(atmEntity),
                () -> assertEquals(null, atmEntity.getId()),
                () -> assertEquals(atmDto.getAddress(), atmEntity.getAddress()),
                () -> assertEquals(atmDto.getStartOfWork(), atmEntity.getStartOfWork()),
                () -> assertEquals(atmDto.getEndOfWork(), atmEntity.getEndOfWork()),
                () -> assertEquals(atmDto.getAllHours(), atmEntity.getAllHours()),
                () -> assertEquals(atmDto.getBranch(), atmEntity.getBranch())
        );
    }

    @Test
    @DisplayName("Маппинг в энтити, на вход передан null")
    void toEntityNullTest() {
        assertNull(mapper.toEntity(null));
    }

    @Test
    @DisplayName("Маппинг в дто")
    void toDtoTest() {
        final AtmEntity atmEntity =
                new AtmEntity(0L, "address", LocalTime.MIN, LocalTime.MAX, Boolean.TRUE, null);

        AtmDto atmDto = mapper.toDto(atmEntity);

        assertAll(
                () -> assertNotNull(atmDto),
                () -> assertEquals(atmEntity.getId(), atmDto.getId()),
                () -> assertEquals(atmEntity.getAddress(), atmDto.getAddress()),
                () -> assertEquals(atmEntity.getStartOfWork(), atmDto.getStartOfWork()),
                () -> assertEquals(atmEntity.getEndOfWork(), atmDto.getEndOfWork()),
                () -> assertEquals(atmEntity.getAllHours(), atmDto.getAllHours()),
                () -> assertEquals(atmEntity.getBranch(), atmDto.getBranch())
        );
    }

    @Test
    @DisplayName("Маппинг в дто, на вход передан null")
    void toDtoNullTest() {
        assertNull(mapper.toDto(null));
    }

    @Test
    @DisplayName("Слияние в энтити")
    void mergeToEntityTest() {
        final AtmEntity atmEntity =
                new AtmEntity(0L, "address", LocalTime.MIN, LocalTime.MAX, Boolean.TRUE, null);
        final AtmDto atmDto =
                new AtmDto(null, "newAddress", LocalTime.NOON, LocalTime.NOON, Boolean.FALSE, null);

        AtmEntity mergedAtmEntity = mapper.mergeToEntity(atmDto, atmEntity);

        assertAll(
                () -> assertNotNull(mergedAtmEntity),
                () -> assertEquals(atmEntity.getId(), mergedAtmEntity.getId()),
                () -> assertEquals(atmDto.getAddress(), mergedAtmEntity.getAddress()),
                () -> assertEquals(atmDto.getStartOfWork(), mergedAtmEntity.getStartOfWork()),
                () -> assertEquals(atmDto.getEndOfWork(), mergedAtmEntity.getEndOfWork()),
                () -> assertEquals(atmDto.getAllHours(), mergedAtmEntity.getAllHours()),
                () -> assertEquals(atmDto.getBranch(), mergedAtmEntity.getBranch())
        );
    }

    @Test
    @DisplayName("Слияние в энтити, на вход передан null")
    void mergeToEntityNullTest() {
        final AtmEntity atmEntity =
                new AtmEntity(0L, "address", LocalTime.MIN, LocalTime.MAX, Boolean.TRUE, null);

        AtmEntity mergedAtmEntity = mapper.mergeToEntity(null, atmEntity);

        assertAll(
                () -> assertNotNull(mergedAtmEntity),
                () -> assertEquals(atmEntity.getId(), mergedAtmEntity.getId()),
                () -> assertEquals(atmEntity.getAddress(), mergedAtmEntity.getAddress()),
                () -> assertEquals(atmEntity.getStartOfWork(), mergedAtmEntity.getStartOfWork()),
                () -> assertEquals(atmEntity.getEndOfWork(), mergedAtmEntity.getEndOfWork()),
                () -> assertEquals(atmEntity.getAllHours(), mergedAtmEntity.getAllHours()),
                () -> assertEquals(atmEntity.getBranch(), mergedAtmEntity.getBranch())
        );
    }

    @Test
    @DisplayName("Маппинг в список дто")
    void toDtoListTest() {
        final AtmEntity atmEntity = new AtmEntity();
        final List<AtmEntity> atmEntityList = List.of(atmEntity, atmEntity, atmEntity);

        List<AtmDto> atmDtoList = mapper.toDtoList(atmEntityList);

        assertEquals(atmEntityList.size(), atmDtoList.size());
    }

    @Test
    @DisplayName("Маппинг в список дто, на вход передан null")
    void toDtoListNullTest() {
        assertNull(mapper.toDtoList(null));
    }
}
