package edu.vsu.putinpa.questionnairesystem.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;

import static java.util.stream.Collectors.joining;

@Getter
public class ValidationException extends AppException {
    private final Errors errors;
    public ValidationException(Errors errors) {
        super(ValidationException.errorsToMessage(errors), HttpStatus.BAD_REQUEST, null);
        this.errors = errors;
    }

    private static String errorsToMessage(Errors errors) {
        return errors.getAllErrors().stream()
                .map(objectError -> objectError.getObjectName() + ": " + objectError.getDefaultMessage())
                .collect(joining("; ", "[", "]"));
    }
}
