package com.bank.antifraud.mappers;

import com.bank.antifraud.dto.AuditDto;
import com.bank.antifraud.entity.AuditEntity;
import org.mapstruct.Mapper;

/**
 * Mapper для {@link AuditEntity} и {@link AuditDto}
 */
@Mapper(componentModel = "spring")
public interface AuditMapper {

    /**
     * @param audit {@link AuditEntity}
     * @return {@link AuditDto}
     */
    AuditDto toDto(AuditEntity audit);
}
