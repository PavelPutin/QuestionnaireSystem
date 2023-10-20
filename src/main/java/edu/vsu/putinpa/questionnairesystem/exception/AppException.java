package edu.vsu.putinpa.questionnairesystem.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public class AppException extends RuntimeException {
    private final HttpStatus httpStatus;
    public AppException(String message, HttpStatus httpStatus, Throwable cause) {
        super(message, cause);
        this.httpStatus = httpStatus;
    }
}
