package com.bank.transfer.service.Impl;

import com.bank.transfer.dto.AuditDto;
import com.bank.transfer.entity.AuditEntity;
import com.bank.transfer.mapper.AuditMapper;
import com.bank.transfer.repository.AuditRepository;
import com.bank.transfer.service.AuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

/**
 * Реализация {@link AuditService}
 */
@Service
@RequiredArgsConstructor
public class AuditServiceImpl implements AuditService {
    private final AuditRepository repository;
    private final AuditMapper mapper;

    /**
     * @param id технический идентификатор {@link AuditEntity}
     * @return {@link AuditDto}
     */
    @Override
    public AuditDto findById(Long id) {
        return mapper.toDto(repository.findById(id)
                .orElseThrow(
                        () -> getException(id)
                )
        );
    }

    private EntityNotFoundException getException(Long id) {
        return new EntityNotFoundException("Не найден аудит с ID  " + id);
    }
}
