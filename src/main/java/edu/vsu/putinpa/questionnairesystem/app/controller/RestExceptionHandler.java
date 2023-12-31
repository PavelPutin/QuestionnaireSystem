package edu.vsu.putinpa.questionnairesystem.app.controller;


import edu.vsu.putinpa.questionnairesystem.api.dto.response.ErrorDto;
import edu.vsu.putinpa.questionnairesystem.exception.AppException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(AppException.class)
    public ResponseEntity<? extends ErrorDto> handleAppException(AppException e) {
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(new ErrorDto(e.getMessage()));
    }
}