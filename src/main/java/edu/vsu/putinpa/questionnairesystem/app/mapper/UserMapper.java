package edu.vsu.putinpa.questionnairesystem.app.mapper;

import edu.vsu.putinpa.questionnairesystem.api.dto.request.RegistrationDTO;
import edu.vsu.putinpa.questionnairesystem.api.dto.request.UserUpdateDTO;
import edu.vsu.putinpa.questionnairesystem.api.dto.response.UserDTO;
import edu.vsu.putinpa.questionnairesystem.app.service.CountryService;
import edu.vsu.putinpa.questionnairesystem.item.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {CountryService.class, CountryMapper.class})
public interface UserMapper {
    User toUser(RegistrationDTO registrationDTO);
    User toUser(UserUpdateDTO registrationDTO);
    List<UserDTO> toDto(List<User> users);
    UserDTO toDto(User user);
}
