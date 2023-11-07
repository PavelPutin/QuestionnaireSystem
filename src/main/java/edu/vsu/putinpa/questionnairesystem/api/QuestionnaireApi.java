package edu.vsu.putinpa.questionnairesystem.api;

import edu.vsu.putinpa.questionnairesystem.api.dto.request.QuestionnaireCreationDTO;
import edu.vsu.putinpa.questionnairesystem.api.dto.request.VoteDTO;
import edu.vsu.putinpa.questionnairesystem.api.dto.response.QuestionnaireBriefDTO;
import edu.vsu.putinpa.questionnairesystem.api.dto.response.QuestionnaireDTO;
import edu.vsu.putinpa.questionnairesystem.app.security.PrincipalDetails;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface QuestionnaireApi {
    @GetMapping
    List<QuestionnaireBriefDTO> getAllBrief();

    @GetMapping("/{name}")
    QuestionnaireDTO getByName(@PathVariable String name);

    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteByName(
            @PathVariable
            String name,
            @AuthenticationPrincipal
            UserDetails user);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    QuestionnaireDTO create(
            @AuthenticationPrincipal
            PrincipalDetails user,
            @RequestBody
            @Valid
            QuestionnaireCreationDTO creationDTO,
            BindingResult errors);

    @PostMapping("/{name}/vote")
    void vote(
            @PathVariable
            String name,
            @AuthenticationPrincipal
            UserDetails user,
            @RequestBody
            @Valid
            VoteDTO voteDTO,
            BindingResult errors);
}
