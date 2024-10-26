package com.bank.profile.service.impl;

import com.bank.profile.dto.AuditDto;
import com.bank.profile.entity.AuditEntity;
import com.bank.profile.mapper.AuditMapper;
import com.bank.profile.repository.AuditRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;


import java.sql.Timestamp;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

/**
 * Тесты для {@link AuditServiceImpl}
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Тесты для AuditServiceImpl")
class AuditServiceImplTest {
    private static final long testId = 1;


    @Mock
    private AuditRepository repository;

    @Mock
    private AuditMapper mapper;

    @InjectMocks
    private AuditServiceImpl service;


    @Test
    @DisplayName("Поиск по id, позитивный сценарий")
    void findByIdPositiveTest() {
        AuditEntity auditEntity = new AuditEntity(
                1L, "hello", "hello", "hello", "hello",
                new Timestamp(55L), new Timestamp(55L), "hello", "hello"
        );
        AuditDto dto = new AuditDto(1L, "hello", "hello", "hello", "hello",
                new Timestamp(55L), new Timestamp(55L), "hello", "hello");

        when(repository.findById(testId)).thenReturn(Optional.of(auditEntity));
        when(mapper.toDto(auditEntity)).thenReturn(dto);

        AuditDto result = service.findById(testId);

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(auditEntity.getId(), result.getId()),
                () -> assertEquals(auditEntity.getEntityType(), result.getEntityType()),
                () -> assertEquals(auditEntity.getOperationType(), result.getOperationType()),
                () -> assertEquals(auditEntity.getCreatedBy(), result.getCreatedBy()),
                () -> assertEquals(auditEntity.getModifiedBy(), result.getModifiedBy()),
                () -> assertEquals(auditEntity.getCreatedAt(), result.getCreatedAt()),
                () -> assertEquals(auditEntity.getModifiedAt(), result.getModifiedAt()),
                () -> assertEquals(auditEntity.getNewEntityJson(), result.getNewEntityJson()),
                () -> assertEquals(auditEntity.getEntityJson(), result.getEntityJson())
        );
    }


    @Test
    @DisplayName("Поиск по несуществующему id, негативный сценарий")
    void findByNonExistIdNegativeTest() {
        when(repository.findById(testId)).thenReturn(Optional.empty());
        assertEquals("Не найден аудит с ID  " + testId,
                assertThrows(EntityNotFoundException.class,
                        () -> service.findById(testId)).getMessage());
    }
}
