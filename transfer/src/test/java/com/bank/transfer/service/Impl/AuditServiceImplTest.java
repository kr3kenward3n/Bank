package com.bank.transfer.service.Impl;

import com.bank.transfer.dto.AuditDto;
import com.bank.transfer.entity.AuditEntity;
import com.bank.transfer.mapper.AuditMapper;
import com.bank.transfer.repository.AuditRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
class AuditServiceImplTest {

    AuditEntity entity;

    AuditDto dto;

    @Mock
    private AuditRepository repository;

    @Mock
    private AuditMapper mapper;

    @InjectMocks
    private AuditServiceImpl service;

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

        dto = new AuditDto();
        dto.setId(1L);
        dto.setEntityType("W");
        dto.setOperationType("W");
        dto.setCreatedBy("W");
        dto.setModifiedBy("W");
        dto.setCreatedAt(new Timestamp(1609459200000L));
        dto.setModifiedAt(new Timestamp(1609459200000L));
        dto.setNewEntityJson("Entity");
        dto.setEntityJson("Entity");
    }

    @Test
    @DisplayName("поиск по id, позитивный сценарий")
    void findByIdPositiveTest() {
        when(repository.findById(anyLong())).thenReturn(Optional.of(entity));
        when(mapper.toDto(entity)).thenReturn(dto);

        AuditDto result = service.findById(anyLong());

        assertAll(
                () -> assertNotNull(result),
                () -> assertEquals(dto.getId(), result.getId()),
                () -> assertEquals(dto.getEntityType(), result.getEntityType()),
                () -> assertEquals(dto.getOperationType(), result.getOperationType()),
                () -> assertEquals(dto.getCreatedBy(), result.getCreatedBy()),
                () -> assertEquals(dto.getModifiedBy(), result.getModifiedBy()),
                () -> assertEquals(dto.getCreatedAt(), result.getCreatedAt()),
                () -> assertEquals(dto.getModifiedAt(), result.getModifiedAt()),
                () -> assertEquals(dto.getEntityJson(), result.getEntityJson()),
                () -> assertEquals(dto.getNewEntityJson(), result.getNewEntityJson())
        );
    }

    @Test
    @DisplayName("поиск по несуществующему id, негативный сценарий")
    void findByNonExistIdNegativeTest() {
        when(repository.findById(anyLong())).thenThrow(EntityNotFoundException.class);

        assertThrows(javax.persistence.EntityNotFoundException.class, () -> {
            service.findById(anyLong());
        });

        verify(mapper, never()).toDto(any(AuditEntity.class));
    }
}