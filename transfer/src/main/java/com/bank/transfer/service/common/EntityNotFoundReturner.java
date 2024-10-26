package com.bank.transfer.service.common;

import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;

/**
 * возвращает {@link EntityNotFoundException}.
 */
@Component
public class EntityNotFoundReturner {

    /**
     * @param id {@link String}
     * @return {@link EntityNotFoundException}
     */
    public EntityNotFoundException getEntityNotFoundException(Long id, String message) {
        return new EntityNotFoundException(message + id);
    }
}
