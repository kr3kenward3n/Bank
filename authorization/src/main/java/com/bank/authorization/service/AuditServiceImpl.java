package com.bank.authorization.service;

import com.bank.authorization.dto.AuditDto;
import com.bank.authorization.repository.entity.AuditEntity;
import com.bank.authorization.mapper.AuditMapper;
import com.bank.authorization.repository.AuditRepository;
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
