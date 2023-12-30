package edu.vsu.putinpa.questionnairesystem.app.controller;


import edu.vsu.putinpa.questionnairesystem.api.AuthApi;
import edu.vsu.putinpa.questionnairesystem.api.dto.request.LoginDto;
import edu.vsu.putinpa.questionnairesystem.api.dto.request.RegistrationDto;
import edu.vsu.putinpa.questionnairesystem.api.dto.response.UserDto;
import edu.vsu.putinpa.questionnairesystem.app.mapper.UserMapper;
import edu.vsu.putinpa.questionnairesystem.app.service.RegistrationService;
import edu.vsu.putinpa.questionnairesystem.exception.AppException;
import edu.vsu.putinpa.questionnairesystem.exception.ValidationException;
import edu.vsu.putinpa.questionnairesystem.item.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
@Log4j2
public class AuthController implements AuthApi {
    private final RegistrationService registrationService;
    private final UserMapper userMapper;
    private final SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();

    @Override
    public void register(RegistrationDto registrationDTO, Errors errors) {
        if (errors.hasErrors()) {
            AppException e = new ValidationException(errors);
            log.info("User tried to register with invalid data: " + e.getMessage());
            throw e;
        }
        User user = userMapper.toUser(registrationDTO);
        registrationService.register(user);
    }

    @Override
    public UserDto login(LoginDto loginDto, Errors errors) {
        if (errors.hasErrors()) {
            AppException e = new ValidationException(errors);
            log.info("User tried to log in with invalid data: " + e.getMessage());
            throw e;
        }
        return userMapper.toDto(registrationService.login(loginDto.username(), loginDto.password()));
    }

    @Override
    public void logout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        logoutHandler.logout(request, response, authentication);
    }
}
