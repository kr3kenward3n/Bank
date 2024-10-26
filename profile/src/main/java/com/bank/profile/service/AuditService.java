package com.bank.profile.service;

import com.bank.profile.dto.AuditDto;
import com.bank.profile.entity.AuditEntity;

/**
 * Сервис для {@link AuditEntity}
 */
public interface AuditService {

    /**
     * @param id технический идентификатор {@link AuditEntity}
     * @return {@link AuditDto}
     */
    AuditDto findById(Long id);
}
