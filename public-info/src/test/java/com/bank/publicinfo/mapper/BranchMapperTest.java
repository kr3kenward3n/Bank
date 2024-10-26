package com.bank.publicinfo.mapper;

import com.bank.publicinfo.dto.BranchDto;
import com.bank.publicinfo.entity.BranchEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тесты для {@link BranchMapper}
 */
class BranchMapperTest {
    private BranchMapper mapper = new BranchMapperImpl();

    @Test
    @DisplayName("Маппинг в энтити")
    void toEntityTest() {
        final BranchDto branchDto =
                new BranchDto(1L, "address", 1L, "city", LocalTime.MIN, LocalTime.MAX);

        BranchEntity branchEntity = mapper.toEntity(branchDto);

        assertAll(
                () -> assertNotNull(branchEntity),
                () -> assertEquals(null, branchEntity.getId()),
                () -> assertEquals(branchDto.getAddress(), branchEntity.getAddress()),
                () -> assertEquals(branchDto.getPhoneNumber(), branchEntity.getPhoneNumber()),
                () -> assertEquals(branchDto.getCity(), branchEntity.getCity()),
                () -> assertEquals(branchDto.getStartOfWork(), branchEntity.getStartOfWork()),
                () -> assertEquals(branchDto.getEndOfWork(), branchEntity.getEndOfWork())
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
        final BranchEntity branchEntity =
                new BranchEntity(1L, "address", 1L, "city", LocalTime.MIN, LocalTime.MAX);

        BranchDto branchDto = mapper.toDto(branchEntity);

        assertAll(
                () -> assertNotNull(branchDto),
                () -> assertEquals(branchEntity.getId(), branchDto.getId()),
                () -> assertEquals(branchEntity.getAddress(), branchDto.getAddress()),
                () -> assertEquals(branchEntity.getPhoneNumber(), branchDto.getPhoneNumber()),
                () -> assertEquals(branchEntity.getCity(), branchDto.getCity()),
                () -> assertEquals(branchEntity.getStartOfWork(), branchDto.getStartOfWork()),
                () -> assertEquals(branchEntity.getEndOfWork(), branchDto.getEndOfWork())
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
        final BranchEntity branchEntity =
                new BranchEntity(1L, "address", 1L, "city", LocalTime.MIN, LocalTime.MAX);
        final BranchDto branchDto =
                new BranchDto(0L, "newAddress", 0L, "newCity", LocalTime.MAX, LocalTime.MIN);

        BranchEntity mergedBranchEntity = mapper.mergeToEntity(branchDto, branchEntity);

        assertAll(
                () -> assertNotNull(mergedBranchEntity),
                () -> assertEquals(branchEntity.getId(), mergedBranchEntity.getId()),
                () -> assertEquals(branchDto.getAddress(), mergedBranchEntity.getAddress()),
                () -> assertEquals(branchDto.getPhoneNumber(), mergedBranchEntity.getPhoneNumber()),
                () -> assertEquals(branchDto.getCity(), mergedBranchEntity.getCity()),
                () -> assertEquals(branchDto.getStartOfWork(), mergedBranchEntity.getStartOfWork()),
                () -> assertEquals(branchDto.getEndOfWork(), mergedBranchEntity.getEndOfWork())
        );
    }

    @Test
    @DisplayName("Слияние в энтити, на вход передан null")
    void mergeToEntityNullTest() {
        final BranchEntity branchEntity =
                new BranchEntity(1L, "address", 1L, "city", LocalTime.MIN, LocalTime.MAX);

        BranchEntity mergedBranchEntity = mapper.mergeToEntity(null, branchEntity);

        assertAll(
                () -> assertNotNull(mergedBranchEntity),
                () -> assertEquals(branchEntity.getId(), mergedBranchEntity.getId()),
                () -> assertEquals(branchEntity.getAddress(), mergedBranchEntity.getAddress()),
                () -> assertEquals(branchEntity.getPhoneNumber(), mergedBranchEntity.getPhoneNumber()),
                () -> assertEquals(branchEntity.getCity(), mergedBranchEntity.getCity()),
                () -> assertEquals(branchEntity.getStartOfWork(), mergedBranchEntity.getStartOfWork()),
                () -> assertEquals(branchEntity.getEndOfWork(), mergedBranchEntity.getEndOfWork())
        );
    }

    @Test
    @DisplayName("Маппинг в список дто")
    void toDtoListTest() {
        final BranchEntity branchEntity = new BranchEntity();
        final List<BranchEntity> branchEntityList = List.of(branchEntity, branchEntity, branchEntity);

        List<BranchDto> branchDtoList = mapper.toDtoList(branchEntityList);

        assertEquals(branchEntityList.size(), branchDtoList.size());
    }

    @Test
    @DisplayName("Маппинг в список дто, на вход передан null")
    void toDtoListNullTest() {
        assertNull(mapper.toDtoList(null));
    }
}
