package edu.vsu.putinpa.questionnairesystem.mapper;

import edu.vsu.putinpa.questionnairesystem.api.dto.request.RegistrationDTO;
import edu.vsu.putinpa.questionnairesystem.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    User toUser(RegistrationDTO registrationDTO);
}
