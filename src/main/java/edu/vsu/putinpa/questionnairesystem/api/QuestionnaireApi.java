package edu.vsu.putinpa.questionnairesystem.api;

import edu.vsu.putinpa.questionnairesystem.api.dto.request.AllBriefRequestDto;
import edu.vsu.putinpa.questionnairesystem.api.dto.request.QuestionnaireCreationDTO;
import edu.vsu.putinpa.questionnairesystem.api.dto.request.VoteDTO;
import edu.vsu.putinpa.questionnairesystem.api.dto.response.AllBriefDto;
import edu.vsu.putinpa.questionnairesystem.api.dto.response.HasUserAnsweredDto;
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
import java.util.UUID;

public interface QuestionnaireApi {
    @GetMapping
    AllBriefDto getAllBrief(
            @Valid
            AllBriefRequestDto allBriefRequestDto,
            BindingResult errors);

    @GetMapping("/popular")
    List<QuestionnaireBriefDTO> getPopular();

    @GetMapping("/{id}")
    QuestionnaireDTO getById(@PathVariable UUID id);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(
            @PathVariable
            UUID id,
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

    @PostMapping("/{id}/vote")
    void vote(
            @PathVariable
            UUID id,
            @AuthenticationPrincipal
            UserDetails user,
            @RequestBody
            @Valid
            VoteDTO voteDTO,
            BindingResult errors);

    @GetMapping("/{id}/hasAnswered")
    HasUserAnsweredDto hasUserAnswered(
            @PathVariable UUID id,
            @AuthenticationPrincipal
            UserDetails user
    );
}
