package edu.vsu.putinpa.questionnairesystem.api;

import edu.vsu.putinpa.questionnairesystem.api.dto.request.LoginDto;
import edu.vsu.putinpa.questionnairesystem.api.dto.request.RegistrationDto;
import edu.vsu.putinpa.questionnairesystem.api.dto.response.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
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
            RegistrationDto intervieweeRegistration,
            Errors errors);

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    UserDto login(
            @RequestBody
            @Valid
            LoginDto loginDto,
            Errors errors
    );

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void logout(Authentication authentication, HttpServletRequest request, HttpServletResponse response);
}
