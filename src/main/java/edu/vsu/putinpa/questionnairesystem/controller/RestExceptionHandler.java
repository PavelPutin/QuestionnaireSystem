package edu.vsu.putinpa.questionnairesystem.controller;


import edu.vsu.putinpa.questionnairesystem.api.dto.response.ErrorDTO;
import edu.vsu.putinpa.questionnairesystem.exception.AppException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(AppException.class)
    public ResponseEntity<? extends ErrorDTO> handleAppException(AppException e) {
        return ResponseEntity
                .status(e.getHttpStatus())
                .body(new ErrorDTO(e.getMessage()));
    }
}