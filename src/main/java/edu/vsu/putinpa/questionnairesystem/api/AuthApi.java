package edu.vsu.putinpa.questionnairesystem.api;

import edu.vsu.putinpa.questionnairesystem.api.dto.request.RegistrationDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface AuthApi {
    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.CREATED)
    void register(
            @RequestBody
            @Valid
            RegistrationDTO intervieweeRegistration,
            Errors errors);
}
