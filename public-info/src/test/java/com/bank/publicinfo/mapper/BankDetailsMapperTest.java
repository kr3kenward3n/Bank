package com.bank.publicinfo.mapper;

import com.bank.publicinfo.dto.BankDetailsDto;
import com.bank.publicinfo.entity.BankDetailsEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тесты для {@link BankDetailsMapper}
 */
class BankDetailsMapperTest {
    private BankDetailsMapper mapper = new BankDetailsMapperImpl();

    @Test
    @DisplayName("Маппинг в энтити")
    void toEntityTest() {
        final BankDetailsDto bankDetailsDto =
                new BankDetailsDto(1L, 1L, 1L, 1L, BigDecimal.ONE,
                        "city", "comp", "name");

        BankDetailsEntity bankDetailsEntity = mapper.toEntity(bankDetailsDto);

        assertAll(
                () -> assertNotNull(bankDetailsEntity),
                () -> assertEquals(null, bankDetailsEntity.getId()),
                () -> assertEquals(bankDetailsDto.getBik(), bankDetailsEntity.getBik()),
                () -> assertEquals(bankDetailsDto.getInn(), bankDetailsEntity.getInn()),
                () -> assertEquals(bankDetailsDto.getKpp(), bankDetailsEntity.getKpp()),
                () -> assertEquals(bankDetailsDto.getCorAccount(), bankDetailsEntity.getCorAccount()),
                () -> assertEquals(bankDetailsDto.getCity(), bankDetailsEntity.getCity()),
                () -> assertEquals(bankDetailsDto.getJointStockCompany(), bankDetailsEntity.getJointStockCompany()),
                () -> assertEquals(bankDetailsDto.getName(), bankDetailsEntity.getName())
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
        final BankDetailsEntity bankDetailsEntity =
                new BankDetailsEntity(1L, 1L, 1L, 1L, BigDecimal.ONE,
                        "city", "comp", "name");

        BankDetailsDto bankDetailsDto = mapper.toDto(bankDetailsEntity);

        assertAll(
                () -> assertNotNull(bankDetailsDto),
                () -> assertEquals(bankDetailsEntity.getId(), bankDetailsDto.getId()),
                () -> assertEquals(bankDetailsEntity.getBik(), bankDetailsDto.getBik()),
                () -> assertEquals(bankDetailsEntity.getInn(), bankDetailsDto.getInn()),
                () -> assertEquals(bankDetailsEntity.getKpp(), bankDetailsDto.getKpp()),
                () -> assertEquals(bankDetailsEntity.getCorAccount(), bankDetailsDto.getCorAccount()),
                () -> assertEquals(bankDetailsEntity.getCity(), bankDetailsDto.getCity()),
                () -> assertEquals(bankDetailsEntity.getJointStockCompany(), bankDetailsDto.getJointStockCompany()),
                () -> assertEquals(bankDetailsEntity.getName(), bankDetailsDto.getName())
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
        final BankDetailsEntity bankDetailsEntity =
                new BankDetailsEntity(1L, 1L, 1L, 1L, BigDecimal.ONE,
                        "city", "comp", "name");
        final BankDetailsDto bankDetailsDto =
                new BankDetailsDto(0L, 0L, 0L, 0L, BigDecimal.TEN,
                        "newCity", "newComp", "newName");

        BankDetailsEntity mergedBankDetailsEntity = mapper.mergeToEntity(bankDetailsDto, bankDetailsEntity);

        assertAll(
                () -> assertNotNull(mergedBankDetailsEntity),
                () -> assertEquals(bankDetailsEntity.getId(), mergedBankDetailsEntity.getId()),
                () -> assertEquals(bankDetailsDto.getBik(), mergedBankDetailsEntity.getBik()),
                () -> assertEquals(bankDetailsDto.getInn(), mergedBankDetailsEntity.getInn()),
                () -> assertEquals(bankDetailsDto.getKpp(), mergedBankDetailsEntity.getKpp()),
                () -> assertEquals(bankDetailsDto.getCorAccount(), mergedBankDetailsEntity.getCorAccount()),
                () -> assertEquals(bankDetailsDto.getCity(), mergedBankDetailsEntity.getCity()),
                () -> assertEquals(bankDetailsDto.getJointStockCompany(), mergedBankDetailsEntity.getJointStockCompany()),
                () -> assertEquals(bankDetailsDto.getName(), mergedBankDetailsEntity.getName())
        );
    }

    @Test
    @DisplayName("Слияние в энтити, на вход передан null")
    void mergeToEntityNullTest() {
        final BankDetailsEntity bankDetailsEntity =
                new BankDetailsEntity(1L, 1L, 1L, 1L, BigDecimal.ONE,
                        "city", "comp", "name");

        BankDetailsEntity mergedBankDetailsEntity = mapper.mergeToEntity(null, bankDetailsEntity);

        assertAll(
                () -> assertNotNull(mergedBankDetailsEntity),
                () -> assertEquals(bankDetailsEntity.getId(), mergedBankDetailsEntity.getId()),
                () -> assertEquals(bankDetailsEntity.getBik(), mergedBankDetailsEntity.getBik()),
                () -> assertEquals(bankDetailsEntity.getInn(), mergedBankDetailsEntity.getInn()),
                () -> assertEquals(bankDetailsEntity.getKpp(), mergedBankDetailsEntity.getKpp()),
                () -> assertEquals(bankDetailsEntity.getCorAccount(), mergedBankDetailsEntity.getCorAccount()),
                () -> assertEquals(bankDetailsEntity.getCity(), mergedBankDetailsEntity.getCity()),
                () -> assertEquals(bankDetailsEntity.getJointStockCompany(), mergedBankDetailsEntity.getJointStockCompany()),
                () -> assertEquals(bankDetailsEntity.getName(), mergedBankDetailsEntity.getName())
        );
    }

    @Test
    @DisplayName("Маппинг в список дто")
    void toDtoListTest() {
        final BankDetailsEntity bankDetailsEntity = new BankDetailsEntity();
        final List<BankDetailsEntity> bankDetailsEntityList = List.of(bankDetailsEntity, bankDetailsEntity, bankDetailsEntity);

        List<BankDetailsDto> bankDetailsDtoList = mapper.toDtoList(bankDetailsEntityList);

        assertEquals(bankDetailsEntityList.size(), bankDetailsDtoList.size());
    }

    @Test
    @DisplayName("Маппинг в список дто, на вход передан null")
    void toDtoListNullTest() {
        assertNull(mapper.toDtoList(null));
    }
}
