package com.bank.profile.mapper;

import com.bank.profile.dto.AuditDto;
import com.bank.profile.entity.AuditEntity;
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
