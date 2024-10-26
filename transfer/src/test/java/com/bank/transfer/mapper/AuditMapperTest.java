package com.bank.transfer.mapper;

import com.bank.transfer.dto.AuditDto;
import com.bank.transfer.entity.AuditEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class AuditMapperTest {

    AuditEntity entity;

    private final AuditMapper mapper = Mappers.getMapper(AuditMapper.class);

    @BeforeEach
    void init() {
        entity = new AuditEntity();
        entity.setId(1L);
        entity.setEntityType("W");
        entity.setOperationType("W");
        entity.setCreatedBy("W");
        entity.setModifiedBy("W");
        entity.setCreatedAt(new Timestamp(1609459200000L));
        entity.setModifiedAt(new Timestamp(1609459200000L));
        entity.setNewEntityJson("Entity");
        entity.setEntityJson("Entity");
    }

    @Test
    @DisplayName("маппинг в дто")
    void toDtoTest() {
        AuditDto result = mapper.toDto(entity);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(entity.getId());
        assertThat(result.getEntityType()).isEqualTo(entity.getEntityType());
        assertThat(result.getOperationType()).isEqualTo(entity.getOperationType());
        assertThat(result.getCreatedBy()).isEqualTo(entity.getCreatedBy());
        assertThat(result.getModifiedBy()).isEqualTo(entity.getModifiedBy());
        assertThat(result.getCreatedAt()).isEqualTo(entity.getCreatedAt());
        assertThat(result.getModifiedAt()).isEqualTo(entity.getModifiedAt());
        assertThat(result.getNewEntityJson()).isEqualTo(entity.getNewEntityJson());
        assertThat(result.getEntityJson()).isEqualTo(entity.getEntityJson());

    }

    @Test
    @DisplayName("маппинг в дто, на вход подан null")
    public void toDtoNullTest() {
        assertNull(mapper.toDto(null));
    }
}