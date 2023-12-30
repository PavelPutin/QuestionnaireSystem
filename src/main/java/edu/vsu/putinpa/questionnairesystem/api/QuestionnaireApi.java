package edu.vsu.putinpa.questionnairesystem.api;

import edu.vsu.putinpa.questionnairesystem.api.dto.request.AllBriefRequestDto;
import edu.vsu.putinpa.questionnairesystem.api.dto.request.QuestionnaireCreationDto;
import edu.vsu.putinpa.questionnairesystem.api.dto.request.VoteDto;
import edu.vsu.putinpa.questionnairesystem.api.dto.response.AllBriefDto;
import edu.vsu.putinpa.questionnairesystem.api.dto.response.HasUserAnsweredDto;
import edu.vsu.putinpa.questionnairesystem.api.dto.response.QuestionnaireBriefDto;
import edu.vsu.putinpa.questionnairesystem.api.dto.response.QuestionnaireDto;
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
    List<QuestionnaireBriefDto> getPopular();

    @GetMapping("/{id}")
    QuestionnaireDto getById(@PathVariable UUID id);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(
            @PathVariable
            UUID id,
            @AuthenticationPrincipal
            UserDetails user);

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    QuestionnaireDto create(
            @AuthenticationPrincipal
            PrincipalDetails user,
            @RequestBody
            @Valid
            QuestionnaireCreationDto creationDTO,
            BindingResult errors);

    @PostMapping("/{id}/vote")
    void vote(
            @PathVariable
            UUID id,
            @AuthenticationPrincipal
            UserDetails user,
            @RequestBody
            @Valid
            VoteDto voteDTO,
            BindingResult errors);

    @GetMapping("/{id}/hasAnswered")
    HasUserAnsweredDto hasUserAnswered(
            @PathVariable UUID id,
            @AuthenticationPrincipal
            UserDetails user
    );
}
