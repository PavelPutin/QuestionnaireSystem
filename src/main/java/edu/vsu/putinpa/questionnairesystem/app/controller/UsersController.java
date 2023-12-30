package edu.vsu.putinpa.questionnairesystem.app.controller;

import edu.vsu.putinpa.questionnairesystem.api.UserApi;
import edu.vsu.putinpa.questionnairesystem.api.dto.request.UserUpdateDTO;
import edu.vsu.putinpa.questionnairesystem.api.dto.response.UserDTO;
import edu.vsu.putinpa.questionnairesystem.app.mapper.UserMapper;
import edu.vsu.putinpa.questionnairesystem.app.service.UsersService;
import edu.vsu.putinpa.questionnairesystem.exception.AppException;
import edu.vsu.putinpa.questionnairesystem.exception.ValidationException;
import edu.vsu.putinpa.questionnairesystem.item.model.User;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@CrossOrigin
@Log4j2
public class UsersController implements UserApi {
    private final UsersService usersService;
    private final UserMapper userMapper;

    @Override
    public List<UserDTO> getAll() {
        return userMapper.toDto(usersService.getAll());
    }

    @Override
    public UserDTO getById(UUID id) {
        return userMapper.toDto(usersService.getById(id));
    }

    @Override
    public void delete(UUID id) {
        usersService.delete(id);
    }

    @Override
    public UserDTO update(UUID id, UserUpdateDTO updateDTO, BindingResult errors) {
        if (errors.hasErrors()) {
            AppException e = new ValidationException(errors);
            log.info("User tried to update profile with invalid data: " + e.getMessage());
            throw e;
        }
        User user = userMapper.toUser(updateDTO);
        return userMapper.toDto(usersService.update(id, user));
    }
}
