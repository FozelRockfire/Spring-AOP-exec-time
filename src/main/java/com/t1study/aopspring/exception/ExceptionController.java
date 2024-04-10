package com.t1study.aopspring.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class ExceptionController {

    @ExceptionHandler(value = {NotFoundException.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorMessage> resourceNotFoundException(RuntimeException exception){
        log.error("resourceNotFoundException: {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ErrorMessage.builder()
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .description(exception.getMessage())
                        .currentTime(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessage> validationException(RuntimeException exception){
        log.error("validationException: {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessage.builder()
                        .statusCode(HttpStatus.BAD_REQUEST.value())
                        .description(exception.getMessage())
                        .currentTime(LocalDateTime.now())
                        .build());
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorMessage> unexpectedErrorException(Exception exception){
        log.error("unexpectedErrorException: {}", exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorMessage.builder()
                        .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .description("Внутренняя ошибка сервера")
                        .currentTime(LocalDateTime.now())
                        .build());
    }
}
