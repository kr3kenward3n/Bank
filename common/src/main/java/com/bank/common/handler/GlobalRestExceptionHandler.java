package com.bank.common.handler;

import com.bank.common.exception.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.TypeMismatchException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.webjars.NotFoundException;

import javax.persistence.EntityNotFoundException;
import java.nio.file.AccessDeniedException;
import java.sql.SQLException;
import java.util.Objects;

/**
 * Глобальный обработчик исключений.
 */
@Slf4j
@RestControllerAdvice
public class GlobalRestExceptionHandler {

    private static final String FORBIDDEN_MESSAGE = "Проверьте Ваши права доступа.";

    private static final String NOT_ACCEPTABLE_MESSAGE = "Вы ввели недопустимые значения.";

    private static final String CONFLICT_MESSAGE = "Проверьте актуальность обновляемых данных.";

    private static final String BAD_REQUEST_MESSAGE = "Проверьте корректность применяемого запроса.";

    private static final String METHOD_NOT_ALLOWED_MESSAGE = "Выбранный вами метод не поддерживается.";

    /**
     * @param ex {@link HttpMessageNotReadableException}.
     * @return {@link ResponseEntity} с текстом ошибки и HttpStatus.BAD_REQUEST.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        final String message = ex.getMessage();
        log.error(message, ex);
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    /**
     * @param ex {@link TypeMismatchException}.
     * @return {@link ResponseEntity} с текстом ошибки и HttpStatus.BAD_REQUEST.
     */
    @ExceptionHandler(TypeMismatchException.class)
    public ResponseEntity<String> handleTypeMismatch(TypeMismatchException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(BAD_REQUEST_MESSAGE, HttpStatus.BAD_REQUEST);
    }

    /**
     * @param ex {@link MethodArgumentTypeMismatchException}.
     * @return {@link ResponseEntity} с текстом ошибки и HttpStatus.BAD_REQUEST.
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(BAD_REQUEST_MESSAGE, HttpStatus.BAD_REQUEST);
    }

    /**
     * @param ex {@link MethodArgumentNotValidException}.
     * @return {@link ResponseEntity} с текстом ошибки и HttpStatus.BAD_REQUEST.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleMethodArgumentTypeMismatch(MethodArgumentNotValidException ex) {

        final String message = Objects.requireNonNull(ex.getFieldError()).getDefaultMessage();

        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    /**
     * @param ex {@link AccessDeniedException}.
     * @return {@link ResponseEntity} с текстом ошибки и HttpStatus.FORBIDDEN.
     */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<String> handleAccessDenied(AccessDeniedException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(FORBIDDEN_MESSAGE, HttpStatus.FORBIDDEN);
    }

    /**
     * @param ex {@link NotFoundException}.
     * @return {@link ResponseEntity} с текстом ошибки и HttpStatus.NOT_FOUND.
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFound(NotFoundException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * @return {@link ResponseEntity} с текстом ошибки и HttpStatus.NOT_FOUND.
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFound(EntityNotFoundException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * @param ex {@link HttpRequestMethodNotSupportedException}.
     * @return {@link ResponseEntity} с текстом ошибки и HttpStatus.METHOD_NOT_ALLOWED.
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(METHOD_NOT_ALLOWED_MESSAGE, HttpStatus.METHOD_NOT_ALLOWED);
    }

    /**
     * @param ex {@link UnsupportedOperationException}.
     * @return {@link ResponseEntity} с текстом ошибки и HttpStatus.NOT_ACCEPTABLE.
     */
    @ExceptionHandler(UnsupportedOperationException.class)
    public ResponseEntity<String> handleUnsupportedOperation(UnsupportedOperationException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(NOT_ACCEPTABLE_MESSAGE, HttpStatus.NOT_ACCEPTABLE);
    }

    /**
     * @param ex {@link HttpClientErrorException}.
     * @return {@link ResponseEntity} с текстом ошибки и HttpStatus.NOT_ACCEPTABLE.
     */
    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<String> handleHttpClientError(HttpClientErrorException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(NOT_ACCEPTABLE_MESSAGE, HttpStatus.NOT_ACCEPTABLE);
    }

    /**
     * @param ex {@link IllegalStateException}.
     * @return {@link ResponseEntity} с текстом ошибки и HttpStatus.CONFLICT.
     */
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleIllegalState(IllegalStateException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(CONFLICT_MESSAGE, HttpStatus.CONFLICT);
    }

    /**
     * @param ex {@link DataIntegrityViolationException}.
     * @return {@link ResponseEntity} с текстом ошибки и HttpStatus.CONFLICT.
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(CONFLICT_MESSAGE, HttpStatus.CONFLICT);
    }

    /**
     * @param ex {@link ValidationException}.
     * @return {@link ResponseEntity} с текстом ошибки и HttpStatus.UNPROCESSABLE_ENTITY.
     */
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> handleValidation(ValidationException ex) {
        final String message = ex.getMessage();
        log.error(message, ex);
        return new ResponseEntity<>(message, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    /**
     * @param ex {@link SQLException}.
     * @return {@link ResponseEntity} с текстом ошибки и HttpStatus.INTERNAL_SERVER_ERROR.
     */
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<String> handleSQL(SQLException ex) {
        final String message = ex.getMessage();
        log.error(message, ex);
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * @param ex {@link Exception}.
     * @return {@link ResponseEntity} с текстом ошибки и HttpStatus.INTERNAL_SERVER_ERROR.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
