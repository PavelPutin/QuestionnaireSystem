package edu.vsu.putinpa.questionnairesystem.controller;


import edu.vsu.putinpa.questionnairesystem.api.AuthApi;
import edu.vsu.putinpa.questionnairesystem.api.dto.request.RegistrationDTO;
import edu.vsu.putinpa.questionnairesystem.service.RegistrationService;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController implements AuthApi {
    private final RegistrationService registrationService;

    public AuthController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @Override
    public void register(RegistrationDTO registrationDTO, Errors errors) {
        registrationService.registerInterviewee(registrationDTO, errors);
    }
}
