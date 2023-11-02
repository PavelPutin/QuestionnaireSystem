package edu.vsu.putinpa.questionnairesystem.item;

import edu.vsu.putinpa.questionnairesystem.item.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OptionsRepository extends JpaRepository<Option, UUID> {
}
