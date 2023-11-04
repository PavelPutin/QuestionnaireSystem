package edu.vsu.putinpa.questionnairesystem.item;

import edu.vsu.putinpa.questionnairesystem.item.model.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface QuestionnairesRepository extends JpaRepository<Questionnaire, UUID> {
    Questionnaire findByName(String name);

    void deleteByName(String name);
}
