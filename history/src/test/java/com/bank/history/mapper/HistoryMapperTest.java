package com.bank.history.mapper;

import com.bank.history.dto.HistoryDto;
import com.bank.history.entity.HistoryEntity;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HistoryMapperTest {

    private final HistoryMapper historyMapper = Mappers.getMapper(HistoryMapper.class);

    @Test
    public void testToEntity() {
        HistoryDto historyDto = HistoryDto.builder()
                .transferAuditId(1L)
                .profileAuditId(2L)
                .accountAuditId(3L)
                .antiFraudAuditId(4L)
                .publicBankInfoAuditId(5L)
                .authorizationAuditId(6L)
                .build();

        HistoryEntity historyEntity = historyMapper.toEntity(historyDto);

        assertEquals(historyDto.getTransferAuditId(), historyEntity.getTransferAuditId());
        assertEquals(historyDto.getProfileAuditId(), historyEntity.getProfileAuditId());
        assertEquals(historyDto.getAccountAuditId(), historyEntity.getAccountAuditId());
        assertEquals(historyDto.getAntiFraudAuditId(), historyEntity.getAntiFraudAuditId());
        assertEquals(historyDto.getPublicBankInfoAuditId(), historyEntity.getPublicBankInfoAuditId());
        assertEquals(historyDto.getAuthorizationAuditId(), historyEntity.getAuthorizationAuditId());
        assertEquals(null, historyEntity.getId());
    }

    @Test
    public void testToDto() {
        HistoryEntity historyEntity = new HistoryEntity();
        historyEntity.setTransferAuditId(1L);
        historyEntity.setProfileAuditId(2L);
        historyEntity.setAccountAuditId(3L);
        historyEntity.setAntiFraudAuditId(4L);
        historyEntity.setPublicBankInfoAuditId(5L);
        historyEntity.setAuthorizationAuditId(6L);

        HistoryDto historyDto = historyMapper.toDto(historyEntity);

        assertEquals(historyEntity.getTransferAuditId(), historyDto.getTransferAuditId());
        assertEquals(historyEntity.getProfileAuditId(), historyDto.getProfileAuditId());
        assertEquals(historyEntity.getAccountAuditId(), historyDto.getAccountAuditId());
        assertEquals(historyEntity.getAntiFraudAuditId(), historyDto.getAntiFraudAuditId());
        assertEquals(historyEntity.getPublicBankInfoAuditId(), historyDto.getPublicBankInfoAuditId());
        assertEquals(historyEntity.getAuthorizationAuditId(), historyDto.getAuthorizationAuditId());
    }

    @Test
    public void testMergeToEntity() {
        HistoryDto historyDto = HistoryDto.builder()
                .transferAuditId(10L)
                .profileAuditId(20L)
                .accountAuditId(30L)
                .antiFraudAuditId(40L)
                .publicBankInfoAuditId(50L)
                .authorizationAuditId(60L)
                .build();

        HistoryEntity historyEntity = new HistoryEntity();
        historyEntity.setTransferAuditId(1L);
        historyEntity.setProfileAuditId(2L);
        historyEntity.setAccountAuditId(3L);
        historyEntity.setAntiFraudAuditId(4L);
        historyEntity.setPublicBankInfoAuditId(5L);
        historyEntity.setAuthorizationAuditId(6L);

        historyMapper.mergeToEntity(historyDto, historyEntity);

        assertEquals(historyDto.getTransferAuditId(), historyEntity.getTransferAuditId());
        assertEquals(historyDto.getProfileAuditId(), historyEntity.getProfileAuditId());
        assertEquals(historyDto.getAccountAuditId(), historyEntity.getAccountAuditId());
        assertEquals(historyDto.getAntiFraudAuditId(), historyEntity.getAntiFraudAuditId());
        assertEquals(historyDto.getPublicBankInfoAuditId(), historyEntity.getPublicBankInfoAuditId());
        assertEquals(historyDto.getAuthorizationAuditId(), historyEntity.getAuthorizationAuditId());
        assertEquals(null, historyEntity.getId());
    }

    @Test
    public void testToListDto() {
        HistoryEntity entity1 = new HistoryEntity();
        entity1.setTransferAuditId(1L);
        entity1.setProfileAuditId(2L);

        HistoryEntity entity2 = new HistoryEntity();
        entity2.setTransferAuditId(3L);
        entity2.setProfileAuditId(4L);

        List<HistoryEntity> historyEntityList = Arrays.asList(entity1, entity2);

        List<HistoryDto> historyDtoList = historyMapper.toListDto(historyEntityList);

        assertEquals(historyEntityList.size(), historyDtoList.size());
        assertEquals(historyEntityList.get(0).getTransferAuditId(), historyDtoList.get(0).getTransferAuditId());
        assertEquals(historyEntityList.get(0).getProfileAuditId(), historyDtoList.get(0).getProfileAuditId());
        assertEquals(historyEntityList.get(1).getTransferAuditId(), historyDtoList.get(1).getTransferAuditId());
        assertEquals(historyEntityList.get(1).getProfileAuditId(), historyDtoList.get(1).getProfileAuditId());
    }
}