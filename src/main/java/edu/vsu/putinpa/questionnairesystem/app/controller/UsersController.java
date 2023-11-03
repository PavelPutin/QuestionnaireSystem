package edu.vsu.putinpa.questionnairesystem.app.controller;

import edu.vsu.putinpa.questionnairesystem.api.UserApi;
import edu.vsu.putinpa.questionnairesystem.api.dto.response.UserDTO;
import edu.vsu.putinpa.questionnairesystem.app.mapper.UserMapper;
import edu.vsu.putinpa.questionnairesystem.app.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/user")
@AllArgsConstructor
public class UsersController implements UserApi {
    private final UsersService usersService;
    private final UserMapper userMapper;

    @Override
    public List<UserDTO> getAll() {
        return userMapper.toDto(usersService.getAll());
    }

    @Override
    public UserDTO getByUsername(String username) {
        return userMapper.toDto(usersService.getByUsername(username));
    }

    @Override
    public void delete(String username) {
        usersService.delete(username);
    }
}
