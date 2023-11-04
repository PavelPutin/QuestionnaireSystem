package edu.vsu.putinpa.questionnairesystem.app.service;

import edu.vsu.putinpa.questionnairesystem.exception.AppException;
import edu.vsu.putinpa.questionnairesystem.item.QuestionnairesRepository;
import edu.vsu.putinpa.questionnairesystem.item.model.Questionnaire;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuestionnairesService {

    private final QuestionnairesRepository questionnairesRepository;

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
}
