package com.bank.publicinfo.mapper;

import com.bank.publicinfo.dto.AuditDto;
import com.bank.publicinfo.entity.AuditEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тесты для {@link AuditMapper}
 */
class AuditMapperTest {
    private AuditMapper mapper = new AuditMapperImpl();

    @Test
    @DisplayName("Маппинг в дто")
    void toDtoTest() {
        final AuditEntity auditEntity =
                new AuditEntity(1L, "Type", "Type", "createdBy", "modifiedBy",
                        new Timestamp(0L), new Timestamp(0L), "Json", "Json");

        AuditDto auditDto = mapper.toDto(auditEntity);

        assertAll(
                () -> assertNotNull(auditDto),
                () -> assertEquals(auditEntity.getId(), auditDto.getId()),
                () -> assertEquals(auditEntity.getEntityType(), auditDto.getEntityType()),
                () -> assertEquals(auditEntity.getOperationType(), auditDto.getOperationType()),
                () -> assertEquals(auditEntity.getCreatedBy(), auditDto.getCreatedBy()),
                () -> assertEquals(auditEntity.getModifiedBy(), auditDto.getModifiedBy()),
                () -> assertEquals(auditEntity.getCreatedAt(), auditDto.getCreatedAt()),
                () -> assertEquals(auditEntity.getModifiedAt(), auditDto.getModifiedAt()),
                () -> assertEquals(auditEntity.getNewEntityJson(), auditDto.getNewEntityJson()),
                () -> assertEquals(auditEntity.getEntityJson(), auditDto.getEntityJson())
        );
    }

    @Test
    @DisplayName("Маппинг в дто, на вход передан null")
    void toDtoNullTest() {
        assertNull(mapper.toDto(null));
    }
}
