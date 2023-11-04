package edu.vsu.putinpa.questionnairesystem.api;

import edu.vsu.putinpa.questionnairesystem.api.dto.request.VoteDTO;
import edu.vsu.putinpa.questionnairesystem.api.dto.response.QuestionnaireBriefDTO;
import edu.vsu.putinpa.questionnairesystem.api.dto.response.QuestionnaireDTO;
import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface QuestionnaireApi {
    @GetMapping
    List<QuestionnaireBriefDTO> getAllBrief();

    @GetMapping("/{name}")
    QuestionnaireDTO getByName(@PathVariable String name);

    @DeleteMapping("/{name}")
    void deleteByName(@PathVariable String name, @AuthenticationPrincipal String username);

    @PostMapping("/{name}/vote")
    void vote(
            @PathVariable String name,
            @AuthenticationPrincipal String username,
            @RequestBody @Valid VoteDTO voteDTO,
            BindingResult errors);
}
