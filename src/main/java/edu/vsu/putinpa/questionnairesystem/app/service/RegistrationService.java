package edu.vsu.putinpa.questionnairesystem.app.service;


import edu.vsu.putinpa.questionnairesystem.api.dto.response.UserDTO;
import edu.vsu.putinpa.questionnairesystem.app.security.PrincipalDetails;
import edu.vsu.putinpa.questionnairesystem.exception.AppException;
import edu.vsu.putinpa.questionnairesystem.item.UserRepository;
import edu.vsu.putinpa.questionnairesystem.item.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RegistrationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
    }

    public User login(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);

        try {
            authenticationManager.authenticate(authenticationToken);
            return repository.getByUsername(username).orElseThrow();
        } catch (AuthenticationException e) {
            throw new AppException("Неправильные логин или пароль", HttpStatus.UNAUTHORIZED, null);
        }
    }
}
