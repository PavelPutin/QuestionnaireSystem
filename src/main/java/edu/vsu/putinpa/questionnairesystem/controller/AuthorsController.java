package edu.vsu.putinpa.questionnairesystem.controller;

import edu.vsu.putinpa.questionnairesystem.dto.response.AuthorProfileDTO;
import edu.vsu.putinpa.questionnairesystem.model.Author;
import edu.vsu.putinpa.questionnairesystem.security.PrincipalDetails;
import edu.vsu.putinpa.questionnairesystem.service.AuthorsService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/author")
public class AuthorsController {

    private final AuthorsService authorsService;

    public AuthorsController(AuthorsService authorsService) {
        this.authorsService = authorsService;
    }

    @GetMapping("/profile")
    public AuthorProfileDTO getAuthorProfile(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        return authorsService.getProfile(principalDetails);
    }
}
