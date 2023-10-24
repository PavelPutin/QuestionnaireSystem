package edu.vsu.putinpa.questionnairesystem.controller;


import edu.vsu.putinpa.questionnairesystem.dto.request.AuthorRegistrationDTO;
import edu.vsu.putinpa.questionnairesystem.dto.request.IntervieweeRegistrationDTO;
import edu.vsu.putinpa.questionnairesystem.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final RegistrationService registrationService;

    public AuthController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/registration/author")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerAuthor(@RequestBody @Valid AuthorRegistrationDTO authorRegistration, Errors errors) {
        registrationService.registerAuthor(authorRegistration, errors);
    }

    @PostMapping("/registration/interviewee")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerInterviewee(@RequestBody @Valid IntervieweeRegistrationDTO intervieweeRegistration, Errors errors) {
        registrationService.registerInterviewee(intervieweeRegistration, errors);
    }
}
