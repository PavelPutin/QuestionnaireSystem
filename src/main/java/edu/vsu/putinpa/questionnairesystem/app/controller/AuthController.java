package edu.vsu.putinpa.questionnairesystem.app.controller;


import edu.vsu.putinpa.questionnairesystem.api.AuthApi;
import edu.vsu.putinpa.questionnairesystem.api.dto.request.LoginDto;
import edu.vsu.putinpa.questionnairesystem.api.dto.request.RegistrationDTO;
import edu.vsu.putinpa.questionnairesystem.app.mapper.UserMapper;
import edu.vsu.putinpa.questionnairesystem.app.service.RegistrationService;
import edu.vsu.putinpa.questionnairesystem.exception.ValidationException;
import edu.vsu.putinpa.questionnairesystem.item.model.User;
import lombok.AllArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@CrossOrigin
public class AuthController implements AuthApi {
    private final RegistrationService registrationService;
    private final UserMapper userMapper;

    @Override
    public void register(RegistrationDTO registrationDTO, Errors errors) {
        if (errors.hasErrors()) {
            throw new ValidationException(errors);
        }
        User user = userMapper.toUser(registrationDTO);
        registrationService.register(user);
    }

    @Override
    public boolean login(LoginDto loginDto, Errors errors) {
        return registrationService.login(loginDto.username(), loginDto.password());
    }
}
