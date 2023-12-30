package edu.vsu.putinpa.questionnairesystem.app.mapper;

import edu.vsu.putinpa.questionnairesystem.api.dto.request.RegistrationDto;
import edu.vsu.putinpa.questionnairesystem.api.dto.request.UserUpdateDto;
import edu.vsu.putinpa.questionnairesystem.api.dto.response.UserDto;
import edu.vsu.putinpa.questionnairesystem.app.service.CountryService;
import edu.vsu.putinpa.questionnairesystem.item.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = {CountryService.class, CountryMapper.class})
public interface UserMapper {
    User toUser(RegistrationDto registrationDTO);
    User toUser(UserUpdateDto registrationDTO);
    List<UserDto> toDto(List<User> users);
    UserDto toDto(User user);
}
