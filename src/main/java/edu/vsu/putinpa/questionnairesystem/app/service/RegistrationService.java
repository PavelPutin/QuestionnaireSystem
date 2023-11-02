package edu.vsu.putinpa.questionnairesystem.app.service;


import edu.vsu.putinpa.questionnairesystem.item.model.User;
import edu.vsu.putinpa.questionnairesystem.item.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RegistrationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
    }
}
