package com.bank.transfer.service;

import com.bank.transfer.dto.AuditDto;
import com.bank.transfer.entity.AuditEntity;

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
