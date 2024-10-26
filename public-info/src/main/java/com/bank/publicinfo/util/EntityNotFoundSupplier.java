package com.bank.publicinfo.util;

import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * Supplier для {@link EntityNotFoundException}
 */
@Component
public class EntityNotFoundSupplier {

    public EntityNotFoundException getException(String message, Long id) {
        return new EntityNotFoundException(message + id);
    }

    public <T> void checkForSizeAndLogging(String message, List<Long> ids, List<T> entities) {
        if (entities.size() < ids.size()) {
            throw new EntityNotFoundException(message + entities + ids);
        }
    }
}
