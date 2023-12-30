package edu.vsu.putinpa.questionnairesystem.app.service;

import edu.vsu.putinpa.questionnairesystem.api.dto.request.OptionCreationDto;
import edu.vsu.putinpa.questionnairesystem.api.dto.request.QuestionnaireCreationDto;
import edu.vsu.putinpa.questionnairesystem.api.dto.request.VoteDto;
import edu.vsu.putinpa.questionnairesystem.exception.AppException;
import edu.vsu.putinpa.questionnairesystem.item.ChoicesRepository;
import edu.vsu.putinpa.questionnairesystem.item.OptionsRepository;
import edu.vsu.putinpa.questionnairesystem.item.QuestionnairesRepository;
import edu.vsu.putinpa.questionnairesystem.item.UserRepository;
import edu.vsu.putinpa.questionnairesystem.item.model.Choice;
import edu.vsu.putinpa.questionnairesystem.item.model.Option;
import edu.vsu.putinpa.questionnairesystem.item.model.Questionnaire;
import edu.vsu.putinpa.questionnairesystem.item.model.User;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Log4j2
public class QuestionnairesService {

    private final QuestionnairesRepository questionnairesRepository;
    private final UsersService usersService;
    private final OptionsRepository optionsRepository;
    private final ChoicesRepository choicesRepository;
    private final UserRepository userRepository;

    public Page<Questionnaire> getAllBrief(Specification<Questionnaire> specification, Pageable pageable) {
        return questionnairesRepository.findAll(specification, pageable);
    }

    public Questionnaire getById(UUID id) {
        return questionnairesRepository.findById(id).orElseThrow(() -> new AppException("Questionnaire not found", HttpStatus.NOT_FOUND, null));
    }

    @Transactional
    public void deleteById(UUID id, String username) {
        questionnairesRepository.findById(id)
                .ifPresent(q -> {
                    if (q.getAuthor().getUsername().equals(username)) {
                        questionnairesRepository.deleteById(id);
                    } else {
                        throw new AppException("You can't delete someone else's questionnaire", HttpStatus.FORBIDDEN, null);
                    }
                });
    }

    @Transactional
    public void vote(UUID id, String username, VoteDto voteDTO) {
        Questionnaire questionnaire = getById(id);
        User user = usersService.getByUsername(username);

        if (questionnaire.getAnswered().contains(user)) {
            AppException e = new AppException("Questionnaire has already answered!", HttpStatus.FORBIDDEN, null);
            log.info("User tried to vote the questionnaire one more time");
            throw e;
        }

        if (!questionnaire.isMultiple() && voteDTO.getOptionsId().size() > 1) {
            AppException e = new AppException("Unacceptable multiple choice!", HttpStatus.BAD_REQUEST, null);
            log.info("User tried to vote for multiple choices while questionnaire isn't multicoicable");
            throw e;
        }

        if (!questionnaire.getOptions().stream().map(Option::getId).toList().containsAll(voteDTO.getOptionsId())) {
            AppException e = new AppException("Some options are not contained by " + id + " questionnaire", HttpStatus.BAD_REQUEST, null);
            log.info("User tried to vote not this questionnaire options");
            throw e;
        }

        questionnaire.getAnswered().add(user);
        questionnairesRepository.save(questionnaire);
        user.getAnswered().add(questionnaire);
        userRepository.save(user);

        for (UUID optionId : voteDTO.getOptionsId()) {
            Option option = optionsRepository.findById(optionId)
                    .orElseThrow(() -> new AppException("Option not found", HttpStatus.NOT_FOUND, null));

            Choice choice = new Choice();
            choice.setAge(user.getAge());
            choice.setGender(user.getGender());
            choice.setMaritalStatus(user.getMaritalStatus());
            choice.setCountry(user.getCountry());
            choice.setOption(option);

            choice = choicesRepository.save(choice);

            option.getChoices().add(choice);
            optionsRepository.save(option);
        }
    }

    @Transactional
    public Questionnaire create(User user, QuestionnaireCreationDto creationDTO) {
        Questionnaire q = new Questionnaire();
        q.setAuthor(user);
        q.setName(creationDTO.getName());
        q.setQuestion(creationDTO.getQuestion());
        q.setMultiple(creationDTO.isMultiple());
        q.setAnswered(new ArrayList<>());
        q.setOptions(new ArrayList<>());

        q = questionnairesRepository.save(q);

        for (OptionCreationDto optionCreationDTO : creationDTO.getOptions()) {
            Option opt = new Option();
            opt.setQuestionnaire(q);
            opt.setText(optionCreationDTO.getText());
            opt.setChoices(new ArrayList<>());
            optionsRepository.save(opt);

            q.getOptions().add(opt);
        }
        return q;
    }

    public List<Questionnaire> getPopular() {
        return questionnairesRepository.getPopular();
    }

    public boolean hasUserAnswered(UUID id, String username) {
        Questionnaire q = questionnairesRepository.findById(id).orElseThrow(() -> new AppException("Questionnaire not found", HttpStatus.NOT_FOUND, null));
        User user = usersService.getByUsername(username);
        return q.getAnswered().contains(user);
    }
}
