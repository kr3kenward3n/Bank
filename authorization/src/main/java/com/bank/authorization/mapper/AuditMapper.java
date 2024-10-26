package com.bank.authorization.mapper;

import com.bank.authorization.dto.AuditDto;
import com.bank.authorization.repository.entity.AuditEntity;
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
