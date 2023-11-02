package edu.vsu.putinpa.questionnairesystem.mapper;

import edu.vsu.putinpa.questionnairesystem.api.dto.request.RegistrationDTO;
import edu.vsu.putinpa.questionnairesystem.model.User;
import edu.vsu.putinpa.questionnairesystem.service.CountryService;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = CountryService.class)
public abstract class UserMapper {
    public abstract User toUser(RegistrationDTO registrationDTO);
}
