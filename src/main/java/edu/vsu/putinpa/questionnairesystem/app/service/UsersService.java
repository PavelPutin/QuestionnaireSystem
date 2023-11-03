package edu.vsu.putinpa.questionnairesystem.app.service;

import edu.vsu.putinpa.questionnairesystem.api.dto.response.UserDTO;
import edu.vsu.putinpa.questionnairesystem.exception.AppException;
import edu.vsu.putinpa.questionnairesystem.item.UserRepository;
import edu.vsu.putinpa.questionnairesystem.item.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UsersService {

    private final UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getByUsername(String username) {
        return userRepository.getByUsername(username).orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND, null));
    }

    public void delete(String username) {
        userRepository.deleteByUsername(username);
    }
}
