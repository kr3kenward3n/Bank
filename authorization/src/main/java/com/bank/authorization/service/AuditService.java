package com.bank.authorization.service;

import com.bank.authorization.dto.AuditDto;
import com.bank.authorization.repository.entity.AuditEntity;

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
