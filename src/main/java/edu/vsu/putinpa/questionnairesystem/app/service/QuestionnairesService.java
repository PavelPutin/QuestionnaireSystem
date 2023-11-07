package edu.vsu.putinpa.questionnairesystem.app.service;

import edu.vsu.putinpa.questionnairesystem.api.dto.request.OptionCreationDTO;
import edu.vsu.putinpa.questionnairesystem.api.dto.request.QuestionnaireCreationDTO;
import edu.vsu.putinpa.questionnairesystem.api.dto.request.VoteDTO;
import edu.vsu.putinpa.questionnairesystem.api.dto.response.QuestionnaireDTO;
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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class QuestionnairesService {

    private final QuestionnairesRepository questionnairesRepository;
    private final UsersService usersService;
    private final OptionsRepository optionsRepository;
    private final ChoicesRepository choicesRepository;
    private final UserRepository userRepository;

    public List<Questionnaire> getAllBrief() {
        return questionnairesRepository.findAll();
    }

    public Questionnaire getByName(String name) {
        return questionnairesRepository.findByName(name).orElseThrow(() -> new AppException("Questionnaire not found", HttpStatus.NOT_FOUND, null));
    }

    @Transactional
    public void deleteByName(String name, String username) {
        questionnairesRepository.findByName(name)
                .ifPresent(q -> {
                    if (q.getAuthor().getUsername().equals(username)) {
                        questionnairesRepository.deleteByName(name);
                    } else {
                        throw new AppException("You can't delete someone else's questionnaire", HttpStatus.FORBIDDEN, null);
                    }
                });
    }

    @Transactional
    public void vote(String name, String username, VoteDTO voteDTO) {
        Questionnaire questionnaire = getByName(name);
        User user = usersService.getByUsername(username);

        if (questionnaire.getAnswered().contains(user)) {
            throw new AppException("Questionnaire has already answered!", HttpStatus.FORBIDDEN, null);
        }

        if (!questionnaire.isMultiple() && voteDTO.getOptionsId().size() > 1) {
            throw new AppException("Unacceptable multiple choice!", HttpStatus.BAD_REQUEST, null);
        }

        if (!questionnaire.getOptions().stream().map(Option::getId).toList().containsAll(voteDTO.getOptionsId())) {
            throw new AppException("Some options are not contained by " + name + " questionnaire", HttpStatus.BAD_REQUEST, null);
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
    public Questionnaire create(User user, QuestionnaireCreationDTO creationDTO) {
        Questionnaire q = new Questionnaire();
        q.setAuthor(user);
        q.setName(creationDTO.getName());
        q.setQuestion(creationDTO.getQuestion());
        q.setMultiple(creationDTO.isMultiple());
        q.setAnswered(new ArrayList<>());
        q.setOptions(new ArrayList<>());

        q = questionnairesRepository.save(q);

        for (OptionCreationDTO optionCreationDTO : creationDTO.getOptions()) {
            Option opt = new Option();
            opt.setQuestionnaire(q);
            opt.setText(optionCreationDTO.getText());
            opt.setChoices(new ArrayList<>());
            optionsRepository.save(opt);

            q.getOptions().add(opt);
        }
        return q;
    }
}
