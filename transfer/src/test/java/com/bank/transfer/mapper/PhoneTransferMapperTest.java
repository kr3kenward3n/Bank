package com.bank.transfer.mapper;

import com.bank.transfer.dto.PhoneTransferDto;
import com.bank.transfer.entity.PhoneTransferEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PhoneTransferMapperTest {

    private final PhoneTransferMapper mapper = Mappers.getMapper(PhoneTransferMapper.class);

    PhoneTransferEntity entity;

    PhoneTransferDto dto;

    @BeforeEach
    void init() {
        dto = new PhoneTransferDto();
        dto.setAmount(new BigDecimal("100.00"));
        dto.setPhoneNumber(1234567890L);
        dto.setPurpose("Test transfer");
        dto.setAccountDetailsId(1L);

        entity = new PhoneTransferEntity();
        entity.setAmount(new BigDecimal("200.00"));
        entity.setPhoneNumber(9876543210L);
        entity.setPurpose("Another test transfer");
        entity.setAccountDetailsId(1L);
    }

    @Test
    @DisplayName("маппинг в энтити")
    void toEntityTest() {
        PhoneTransferEntity result = mapper.toEntity(dto);

        assertThat(result).isNotNull();
        assertThat(dto.getId()).isEqualTo(result.getId());
        assertThat(dto.getPhoneNumber()).isEqualTo(result.getPhoneNumber());
        assertThat(dto.getAmount()).isEqualTo(result.getAmount());
        assertThat(dto.getPurpose()).isEqualTo(result.getPurpose());
        assertThat(dto.getAccountDetailsId()).isEqualTo(result.getAccountDetailsId());
    }

    @Test
    @DisplayName("маппинг в энтити, на вход подан null")
    public void toEntityNullTest() {
        assertNull(mapper.toEntity(null));
    }

    @Test
    @DisplayName("маппинг в дто")
    void toDtoTest() {
        PhoneTransferDto dto = mapper.toDto(entity);

        assertThat(dto).isNotNull();
        assertThat(dto.getId()).isEqualTo(entity.getId());
        assertThat(dto.getPhoneNumber()).isEqualTo(entity.getPhoneNumber());
        assertThat(dto.getAmount()).isEqualTo(entity.getAmount());
        assertThat(dto.getPurpose()).isEqualTo(entity.getPurpose());
        assertThat(dto.getAccountDetailsId()).isEqualTo(entity.getAccountDetailsId());
    }

    @Test
    @DisplayName("маппинг в дто, на вход подан null")
    public void toDtoNullTest() {
        assertNull(mapper.toDto(null));
    }

    @Test
    @DisplayName("слияние в энтити")
    void mergeToEntityTest() {
        assertAll(
                () -> assertNotNull(mapper.mergeToEntity(dto, entity)),
                () -> assertEquals(mapper.mergeToEntity(dto, entity), entity)
        );
    }

    @Test
    @DisplayName("слияние в энтити, на вход подан null")
    public void mergeToEntityNullTest() {
        assertEquals(mapper.mergeToEntity(null, entity), entity);
    }

    @Test
    @DisplayName("маппинг в список дто")
    void toListDtoTest() {
        List<PhoneTransferDto> result = mapper.toDtoList(new ArrayList<>(List.of(entity, entity, entity)));

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(entity.getId(), result.get(0).getId()),
                () -> assertEquals(entity.getId(), result.get(1).getId()),
                () -> assertEquals(entity.getId(), result.get(2).getId())
        );
    }

    @Test
    @DisplayName("маппинг в список дто, на вход подан null")
    public void toEmptyListDtoTest() {
        assertNull(mapper.toDtoList(null));
    }
}