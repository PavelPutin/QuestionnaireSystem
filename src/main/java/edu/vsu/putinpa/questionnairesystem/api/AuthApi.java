package edu.vsu.putinpa.questionnairesystem.api;

import edu.vsu.putinpa.questionnairesystem.api.dto.request.AuthorRegistrationDTO;
import edu.vsu.putinpa.questionnairesystem.api.dto.request.IntervieweeRegistrationDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

public interface AuthApi {
    @PostMapping("/registration/author")
    @ResponseStatus(HttpStatus.CREATED)
    void registerAuthor(@RequestBody @Valid AuthorRegistrationDTO authorRegistration, Errors errors);

    @PostMapping("/registration/interviewee")
    @ResponseStatus(HttpStatus.CREATED)
    void registerInterviewee(@RequestBody @Valid IntervieweeRegistrationDTO intervieweeRegistration, Errors errors);
}
