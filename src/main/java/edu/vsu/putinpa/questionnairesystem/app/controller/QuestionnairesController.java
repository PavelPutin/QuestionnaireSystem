package edu.vsu.putinpa.questionnairesystem.app.controller;

import edu.vsu.putinpa.questionnairesystem.api.QuestionnaireApi;
import edu.vsu.putinpa.questionnairesystem.api.dto.request.AllBriefRequestDto;
import edu.vsu.putinpa.questionnairesystem.api.dto.request.QuestionnaireCreationDTO;
import edu.vsu.putinpa.questionnairesystem.api.dto.request.VoteDTO;
import edu.vsu.putinpa.questionnairesystem.api.dto.response.AllBriefDto;
import edu.vsu.putinpa.questionnairesystem.api.dto.response.QuestionnaireBriefDTO;
import edu.vsu.putinpa.questionnairesystem.api.dto.response.QuestionnaireDTO;
import edu.vsu.putinpa.questionnairesystem.app.mapper.QuestionnaireMapper;
import edu.vsu.putinpa.questionnairesystem.app.security.PrincipalDetails;
import edu.vsu.putinpa.questionnairesystem.app.service.QuestionnairesService;
import edu.vsu.putinpa.questionnairesystem.exception.ValidationException;
import edu.vsu.putinpa.questionnairesystem.item.QuestionnairesRepository;
import edu.vsu.putinpa.questionnairesystem.item.model.Questionnaire;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static edu.vsu.putinpa.questionnairesystem.item.QuestionnairesRepository.hasAuthor;
import static edu.vsu.putinpa.questionnairesystem.item.QuestionnairesRepository.nameContains;
import static org.springframework.data.jpa.domain.Specification.where;

@RestController
@RequestMapping("/questionnaire")
@RequiredArgsConstructor
@PropertySource("classpath:application.yml")
public class QuestionnairesController implements QuestionnaireApi {
    @Value("${questionnaire.default.page.number}")
    private int PAGE_NUMBER;
    @Value("${questionnaire.default.page.size}")
    private int PAGE_SIZE;

    private final QuestionnairesService questionnairesService;
    private final QuestionnaireMapper questionnaireMapper;

    @Override
    public AllBriefDto getAllBrief(AllBriefRequestDto allBriefRequestDto, BindingResult errors) {
        if (errors.hasErrors()) {
            throw new ValidationException(errors);
        }

        int pageNumber = allBriefRequestDto.getPageNumber() == null ? PAGE_NUMBER : allBriefRequestDto.getPageNumber();
        int pageSize = allBriefRequestDto.getPageSize() == null ? PAGE_SIZE : allBriefRequestDto.getPageSize();
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        String name = allBriefRequestDto.getQuestionnaireNameSearch() == null ? "" : allBriefRequestDto.getQuestionnaireNameSearch();
        String authorName = allBriefRequestDto.getAuthorNameSearch() == null ? "" : allBriefRequestDto.getAuthorNameSearch();
        Specification<Questionnaire> specification = where(nameContains(name)).and(hasAuthor(authorName));

        Page<Questionnaire> page = questionnairesService.getAllBrief(specification, pageable);
        AllBriefDto responseBody = new AllBriefDto();

        List<QuestionnaireBriefDTO> briefDTOList = questionnaireMapper.toDto(page.getContent());
        responseBody.setBriefDTOList(briefDTOList);

        responseBody.setNumber(page.getNumber());
        responseBody.setHasNext(page.hasNext());
        responseBody.setHasPrevious(page.hasPrevious());
        responseBody.setTotalPages(page.getTotalPages());

        return responseBody;
    }

    @Override
    public QuestionnaireDTO getByName(UUID id) {
        return questionnaireMapper.toDto(questionnairesService.getById(id));
    }

    @Override
    public void deleteById(UUID id, UserDetails user) {
        questionnairesService.deleteById(id, user.getUsername());
    }

    @Override
    public QuestionnaireDTO create(PrincipalDetails user, QuestionnaireCreationDTO creationDTO, BindingResult errors) {
        if (errors.hasErrors()) {
            throw new ValidationException(errors);
        }

        return questionnaireMapper.toDto(questionnairesService.create(user.user(), creationDTO));
    }

    @Override
    public void vote(UUID id, UserDetails user, VoteDTO voteDTO, BindingResult errors) {
        if (errors.hasErrors()) {
            throw new ValidationException(errors);
        }
        questionnairesService.vote(id, user.getUsername(), voteDTO);
    }
}
