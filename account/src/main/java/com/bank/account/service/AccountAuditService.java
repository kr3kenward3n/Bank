package com.bank.account.service;

import com.bank.account.dto.AuditDto;
import com.bank.account.entity.AuditEntity;

/**
 * Сервис для {@link AuditEntity}
 */
public interface AccountAuditService {

    /**
     * @param id технический идентификатор {@link AuditEntity}
     * @return {@link AuditDto}
     */
    AuditDto findById(Long id);
}
