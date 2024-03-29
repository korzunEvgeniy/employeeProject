package com.mastery.java.task.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.jms.JmsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ControllerExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage employeeNotFoundException(EmployeeNotFoundException ex, WebRequest request) {
        logger.error(ex.toString());
        return new ErrorMessage(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false));
    }

    @ExceptionHandler(JmsException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage jmsException(JmsException ex, WebRequest request) {
        logger.error(ex.toString());
        return new ErrorMessage(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage globalExceptionHandler(Exception ex, WebRequest request) {
        logger.error(ex.toString());
        return new ErrorMessage(
                LocalDateTime.now(),
                ex.getMessage(),
                request.getDescription(false));
    }
}
