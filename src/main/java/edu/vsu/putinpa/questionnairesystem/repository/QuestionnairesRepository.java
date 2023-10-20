package edu.vsu.putinpa.questionnairesystem.repository;

import edu.vsu.putinpa.questionnairesystem.model.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface QuestionnairesRepository extends JpaRepository<Questionnaire, UUID> {
}
