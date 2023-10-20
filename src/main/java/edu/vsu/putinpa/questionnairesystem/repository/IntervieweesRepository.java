package edu.vsu.putinpa.questionnairesystem.repository;

import edu.vsu.putinpa.questionnairesystem.model.Interviewee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IntervieweesRepository extends JpaRepository<Interviewee, UUID> {
}
