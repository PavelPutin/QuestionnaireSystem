package edu.vsu.putinpa.questionnairesystem.repository;

import edu.vsu.putinpa.questionnairesystem.model.Choice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChoicesRepository extends JpaRepository<Choice, UUID> {
}
