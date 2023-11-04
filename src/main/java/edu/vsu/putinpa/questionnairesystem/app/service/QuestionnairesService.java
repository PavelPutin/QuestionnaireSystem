package edu.vsu.putinpa.questionnairesystem.app.service;

import edu.vsu.putinpa.questionnairesystem.api.dto.request.VoteDTO;
import edu.vsu.putinpa.questionnairesystem.exception.AppException;
import edu.vsu.putinpa.questionnairesystem.item.ChoicesRepository;
import edu.vsu.putinpa.questionnairesystem.item.OptionsRepository;
import edu.vsu.putinpa.questionnairesystem.item.QuestionnairesRepository;
import edu.vsu.putinpa.questionnairesystem.item.model.Choice;
import edu.vsu.putinpa.questionnairesystem.item.model.Option;
import edu.vsu.putinpa.questionnairesystem.item.model.Questionnaire;
import edu.vsu.putinpa.questionnairesystem.item.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class QuestionnairesService {

    private final QuestionnairesRepository questionnairesRepository;
    private final UsersService usersService;
    private final OptionsRepository optionsRepository;
    private final ChoicesRepository choicesRepository;

    public List<Questionnaire> getAllBrief() {
        return questionnairesRepository.findAll();
    }

    public Questionnaire getByName(String name) {
        return questionnairesRepository.findByName(name).orElseThrow(() -> new AppException("Questionnaire not found", HttpStatus.NOT_FOUND, null));
    }

    public void deleteByName(String name) {
        questionnairesRepository.findByName(name)
                .ifPresent(q -> {
                    if (q.getAuthor().getUsername().equals(name)) {
                        questionnairesRepository.deleteByName(name);
                    } else {
                        throw new AppException("You can't delete someone else's questionnaire", HttpStatus.UNAUTHORIZED, null);
                    }
                });
    }

    @Transactional
    public void vote(String name, String username, VoteDTO voteDTO) {
        Questionnaire questionnaire = getByName(name);
        User user = usersService.getByUsername(username);

        questionnaire.getAnswered().add(user);
        questionnairesRepository.save(questionnaire);

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
}
