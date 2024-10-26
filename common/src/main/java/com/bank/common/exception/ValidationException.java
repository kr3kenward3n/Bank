package com.bank.common.exception;

/**
 * Exception при не корректной валидации полей.
 */
public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }
}
