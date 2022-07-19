package ru.ivanov.paymentsservice.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.ivanov.paymentsservice.dto.ErrorDTO;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        return new ErrorDTO(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(DataRetrievalFailureException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleDataRetrievalFailureEx(DataRetrievalFailureException exception) {
        return new ErrorDTO(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorDTO handleConstraintViolationException(ConstraintViolationException exception) {
        String[] arr = exception.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessageTemplate).toArray(String[]::new);
        return new ErrorDTO(HttpStatus.BAD_REQUEST, arr);
    }
}
