package edu.vsu.putinpa.questionnairesystem.app.controller;


import edu.vsu.putinpa.questionnairesystem.api.AuthApi;
import edu.vsu.putinpa.questionnairesystem.api.dto.request.LoginDto;
import edu.vsu.putinpa.questionnairesystem.api.dto.request.RegistrationDTO;
import edu.vsu.putinpa.questionnairesystem.api.dto.response.UserDTO;
import edu.vsu.putinpa.questionnairesystem.app.mapper.UserMapper;
import edu.vsu.putinpa.questionnairesystem.app.service.RegistrationService;
import edu.vsu.putinpa.questionnairesystem.exception.ValidationException;
import edu.vsu.putinpa.questionnairesystem.item.model.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController implements AuthApi {
    private static Logger logger = LogManager.getLogger(AuthController.class);

    private final RegistrationService registrationService;
    private final UserMapper userMapper;
    private final SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();

    @Override
    public void register(RegistrationDTO registrationDTO, Errors errors) {
        logger.info("Get request to registration with data " + registrationDTO);
        if (errors.hasErrors()) {
            throw new ValidationException(errors);
        }
        User user = userMapper.toUser(registrationDTO);
        registrationService.register(user);
    }

    @Override
    public UserDTO login(LoginDto loginDto, Errors errors) {
        logger.info("Get request to login with data " + loginDto);
        if (errors.hasErrors()) {
            throw new ValidationException(errors);
        }
        return userMapper.toDto(registrationService.login(loginDto.username(), loginDto.password()));
    }

    @Override
    public void logout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
        logoutHandler.logout(request, response, authentication);
    }
}
