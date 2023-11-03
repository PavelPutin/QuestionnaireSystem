package edu.vsu.putinpa.questionnairesystem.item;

import edu.vsu.putinpa.questionnairesystem.item.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> getByUsername(String username);
    void deleteByUsername(String username);
}
