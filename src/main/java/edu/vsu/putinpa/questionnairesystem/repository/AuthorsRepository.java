package edu.vsu.putinpa.questionnairesystem.repository;

import edu.vsu.putinpa.questionnairesystem.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorsRepository extends JpaRepository<Author, UUID> {
}
