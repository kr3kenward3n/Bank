package com.bank.publicinfo.service.impl;

import com.bank.publicinfo.dto.AuditDto;
import com.bank.publicinfo.entity.AuditEntity;
import com.bank.publicinfo.mapper.AuditMapper;
import com.bank.publicinfo.repository.AuditRepository;
import com.bank.publicinfo.service.AuditService;
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
