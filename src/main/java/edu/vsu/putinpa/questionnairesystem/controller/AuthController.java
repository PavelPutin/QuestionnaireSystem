package edu.vsu.putinpa.questionnairesystem.controller;


import edu.vsu.putinpa.questionnairesystem.api.AuthApi;
import edu.vsu.putinpa.questionnairesystem.api.dto.request.AuthorRegistrationDTO;
import edu.vsu.putinpa.questionnairesystem.api.dto.request.IntervieweeRegistrationDTO;
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
    public void registerAuthor(AuthorRegistrationDTO authorRegistration, Errors errors) {
        registrationService.registerAuthor(authorRegistration, errors);
    }

    @Override
    public void registerInterviewee(IntervieweeRegistrationDTO intervieweeRegistration, Errors errors) {
        registrationService.registerInterviewee(intervieweeRegistration, errors);
    }
}
