package com.bank.profile.mapper;

import com.bank.profile.dto.AccountDetailsIdDto;
import com.bank.profile.dto.ProfileDto;
import com.bank.profile.entity.AccountDetailsIdEntity;
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
 * Тесты для {@link AccountDetailsIdMapper}
 */
@DisplayName("Тесты для AccountDetailsIdMapper")
class AccountDetailsIdMapperTest {

    private AccountDetailsIdDto dto;
    private AccountDetailsIdEntity entity;

    private final AccountDetailsIdMapper mapper = new AccountDetailsIdMapperImpl();

    @BeforeEach
    void createEntityAndDto() {
        dto = new AccountDetailsIdDto(1L, 1L, new ProfileDto());
        entity = new AccountDetailsIdEntity(2L, 1L, new ProfileEntity());
    }


    @Test
    @DisplayName("Маппинг в дто")
    void toDtoTest() {
        AccountDetailsIdDto result = mapper.toDto(entity);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(entity.getId(), result.getId()),
                () -> assertEquals(entity.getAccountId(), result.getAccountId())
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
        AccountDetailsIdEntity result = mapper.toEntity(dto);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(dto.getAccountId(), result.getAccountId())
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
        AccountDetailsIdEntity result = mapper.mergeToEntity(dto, entity);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(entity.getId(), result.getId()),
                () -> assertEquals(entity.getAccountId(), result.getAccountId())
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
        List<AccountDetailsIdDto> result = mapper.toDtoList(new ArrayList<>(List.of(entity, entity, entity)));

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