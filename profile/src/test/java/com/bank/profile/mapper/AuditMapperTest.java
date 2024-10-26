package com.bank.profile.mapper;

import com.bank.profile.entity.AuditEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * Тесты для {@link AuditMapper}
 */
@DisplayName("Тесты для AuditMapper")
class AuditMapperTest {

    private AuditEntity entity;

    private final AuditMapper mapper = new AuditMapperImpl();


    @BeforeEach
    void createEntity() {
        entity = new AuditEntity(1L, "hello", "hello", "hello", "hello",
                new Timestamp(55L), new Timestamp(55L), "hello", "hello");
    }


    @Test
    @DisplayName("Маппинг в дто")
    void toDtoTest() {
        assertAll(
                () -> assertNotNull(mapper.toDto(entity)),
                () -> assertEquals(entity.getId(), mapper.toDto(entity).getId()),
                () -> assertEquals(entity.getEntityType(), mapper.toDto(entity).getEntityType()),
                () -> assertEquals(entity.getOperationType(), mapper.toDto(entity).getOperationType()),
                () -> assertEquals(entity.getCreatedBy(), mapper.toDto(entity).getCreatedBy()),
                () -> assertEquals(entity.getModifiedBy(), mapper.toDto(entity).getModifiedBy()),
                () -> assertEquals(entity.getCreatedAt(), mapper.toDto(entity).getCreatedAt()),
                () -> assertEquals(entity.getModifiedAt(), mapper.toDto(entity).getModifiedAt()),
                () -> assertEquals(entity.getNewEntityJson(), mapper.toDto(entity).getNewEntityJson()),
                () -> assertEquals(entity.getEntityJson(), mapper.toDto(entity).getEntityJson())
        );
    }
}