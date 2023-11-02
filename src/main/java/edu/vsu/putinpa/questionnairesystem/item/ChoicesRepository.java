package edu.vsu.putinpa.questionnairesystem.item;

import edu.vsu.putinpa.questionnairesystem.item.model.Choice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChoicesRepository extends JpaRepository<Choice, UUID> {
}
