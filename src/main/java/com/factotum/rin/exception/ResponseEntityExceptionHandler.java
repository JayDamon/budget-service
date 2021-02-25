package com.factotum.rin.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ResponseEntityExceptionHandler extends org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ResponseEntityExceptionHandler.class);

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ExceptionHandler(value = {ConstraintViolationException.class})
    protected ResponseEntity<?> handleIllegalArgumentException(ConstraintViolationException e, WebRequest request) {
        log.error(e.getMessage(), e);

        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST,
                request);
    }

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ExceptionHandler(value = {IllegalArgumentException.class})
    protected ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException e, WebRequest request) {
        log.error(e.getMessage(), e);
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ExceptionHandler(value = {NoResultException.class})
    protected ResponseEntity<?> handleResourceNotFound(NoResultException e, WebRequest request) {
        log.error("Resource not found, " + e, e);
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND,
                request);
    }

    @Order(Ordered.HIGHEST_PRECEDENCE)
    @ExceptionHandler(value = {EntityNotFoundException.class})
    protected ResponseEntity<?> handleResourceNotFound(EntityNotFoundException e, WebRequest request) {
        log.error("Resource not found, " + e, e);
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND,
                request);
    }

}
