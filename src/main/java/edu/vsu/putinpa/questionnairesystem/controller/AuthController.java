package edu.vsu.putinpa.questionnairesystem.controller;


import edu.vsu.putinpa.questionnairesystem.dto.request.AuthorRegistrationDTO;
import edu.vsu.putinpa.questionnairesystem.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final RegistrationService registrationService;

    public AuthController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/registration/author")
    public void registerAuthor(@RequestBody @Valid AuthorRegistrationDTO authorRegistration, Errors errors) {
        registrationService.registerAuthor(authorRegistration, errors);
    }
}
