package edu.vsu.putinpa.questionnairesystem.api;

import edu.vsu.putinpa.questionnairesystem.api.dto.request.LoginDto;
import edu.vsu.putinpa.questionnairesystem.api.dto.request.RegistrationDTO;
import edu.vsu.putinpa.questionnairesystem.api.dto.response.UserDTO;
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

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    UserDTO login(
            @RequestBody
            @Valid
            LoginDto loginDto,
            Errors errors
    );
}
