package com.bank.history.mapper;

import com.bank.history.dto.HistoryDto;
import com.bank.history.entity.HistoryEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Модульные тесты для {@link HistoryMapper}
 */
@DisplayName("Тесты HistoryMapper")
public class HistoryMapperTest {

    private final HistoryMapper mapper = new HistoryMapperImpl();
    private final Long id = 1L;
    private final HistoryDto historyDto = HistoryDto.builder()
            .id(id)
            .accountAuditId(id)
            .antiFraudAuditId(id)
            .authorizationAuditId(id)
            .profileAuditId(id)
            .publicBankInfoAuditId(id)
            .transferAuditId(id)
            .build();

    private final HistoryEntity historyEntity = new HistoryEntity(id, id, id, id, id, id, id);

    @Test
    @DisplayName("Маппинг в entity")
    public void toEntityTest() {
        assertAll(
                () -> assertNotNull(mapper.toEntity(historyDto)),
                () -> assertNotEquals(mapper.toEntity(historyDto).getId(), historyDto.getId()),
                () -> assertEquals(
                        mapper.toEntity(historyDto).getAccountAuditId(),
                        historyEntity.getAccountAuditId()),
                () -> assertEquals(
                        mapper.toEntity(historyDto).getAntiFraudAuditId(),
                        historyEntity.getAntiFraudAuditId()),
                () -> assertEquals(
                        mapper.toEntity(historyDto).getAuthorizationAuditId(),
                        historyEntity.getAuthorizationAuditId()),
                () -> assertEquals(
                        mapper.toEntity(historyDto).getProfileAuditId(),
                        historyEntity.getProfileAuditId()),
                () -> assertEquals(
                        mapper.toEntity(historyDto).getPublicBankInfoAuditId(),
                        historyEntity.getPublicBankInfoAuditId()),
                () -> assertEquals(
                        mapper.toEntity(historyDto).getTransferAuditId(),
                        historyEntity.getTransferAuditId())
        );
    }

    @Test
    @DisplayName("Маппинг в entity, передан null")
    public void toEntityNullTest() {
        assertNull(mapper.toEntity(null));
    }

    @Test
    @DisplayName("Маппинг в dto")
    public void toDtoTest() {
        assertAll(
                () -> assertNotNull(mapper.toDto(historyEntity)),
                () -> assertEquals(mapper.toDto(historyEntity).getId(), historyDto.getId()),
                () -> assertEquals(mapper.toDto(historyEntity).getAccountAuditId(), historyDto.getAccountAuditId()),
                () -> assertEquals(
                        mapper.toDto(historyEntity).getAntiFraudAuditId(),
                        historyDto.getAntiFraudAuditId()),
                () -> assertEquals(
                        mapper.toDto(historyEntity).getAuthorizationAuditId(),
                        historyDto.getAuthorizationAuditId()),
                () -> assertEquals(
                        mapper.toDto(historyEntity).getProfileAuditId(),
                        historyDto.getProfileAuditId()),
                () -> assertEquals(
                        mapper.toDto(historyEntity).getPublicBankInfoAuditId(),
                        historyDto.getPublicBankInfoAuditId()),
                () -> assertEquals(
                        mapper.toDto(historyEntity).getTransferAuditId(),
                        historyDto.getTransferAuditId())
        );
    }

    @Test
    @DisplayName("Маппинг в dto, передан null")
    public void toDtoNullTest() {
        assertNull(mapper.toDto(null));
    }

    @Test
    @DisplayName("Слияние в entity")
    public void mergeToEntityTest() {
        assertAll(
                () -> assertNotNull(mapper.mergeToEntity(historyDto, historyEntity)),
                () -> assertEquals(mapper.mergeToEntity(historyDto, historyEntity), historyEntity)
        );
    }

    @Test
    @DisplayName("Слияние в entity, передан null")
    public void mergeToEntityNullTest() {
        assertEquals(mapper.mergeToEntity(null, historyEntity), historyEntity);
    }

    @Test
    @DisplayName("Маппинг списка в dto")
    public void toListDtoTest() {
        final List<HistoryDto> historyDtos = List.of(
                historyDto,
                new HistoryDto(2L, 2L, 2L, 2L,
                        2L, 2L, 2L),
                new HistoryDto(3L, 3L, 3L, 3L,
                        3L, 3L, 3L)
        );
        final List<HistoryEntity> historyEntities = List.of(
                historyEntity,
                new HistoryEntity(2L, 2L, 2L, 2L,
                        2L, 2L, 2L),
                new HistoryEntity(3L, 3L, 3L, 3L,
                        3L, 3L, 3L)
        );
        assertNotNull(mapper.toListDto(historyEntities));
        final List<HistoryDto> historiesMapped = mapper.toListDto(historyEntities);
        assertAll(
                () -> assertEquals(historiesMapped.get(0).getId(), historyDtos.get(0).getId()),
                () -> assertEquals(historiesMapped.get(1).getId(), historyDtos.get(1).getId()),
                () -> assertEquals(historiesMapped.get(2).getId(), historyDtos.get(2).getId())
        );
    }

    @Test
    @DisplayName("Маппинг в список dto, передан null")
    public void toListDtoNullTest() {
        assertNull(mapper.toListDto(null));
    }

}
