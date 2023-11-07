package edu.vsu.putinpa.questionnairesystem.app.service;

import edu.vsu.putinpa.questionnairesystem.exception.AppException;
import edu.vsu.putinpa.questionnairesystem.item.UserRepository;
import edu.vsu.putinpa.questionnairesystem.item.model.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void delete(String username) {
        userRepository.deleteByUsername(username);
    }

    @Transactional
    public User update(String username, User user) {
        User toUpdate = getByUsername(username);
        toUpdate.setAge(user.getAge());
        toUpdate.setCountry(user.getCountry());
        toUpdate.setGender(user.getGender());
        toUpdate.setMaritalStatus(user.getMaritalStatus());
        userRepository.save(toUpdate);
        return toUpdate;
    }
}
