package edu.vsu.putinpa.questionnairesystem.repository;

import edu.vsu.putinpa.questionnairesystem.model.Principal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PrincipalsRepository extends JpaRepository<Principal, UUID> {
    Optional<Principal> getPrincipalByUsername(String string);
}
