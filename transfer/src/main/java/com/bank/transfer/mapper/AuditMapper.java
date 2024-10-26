package com.bank.transfer.mapper;

import com.bank.transfer.dto.AuditDto;
import com.bank.transfer.entity.AuditEntity;
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
