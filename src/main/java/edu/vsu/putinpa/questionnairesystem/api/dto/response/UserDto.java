package edu.vsu.putinpa.questionnairesystem.api.dto.response;

import edu.vsu.putinpa.questionnairesystem.item.model.Gender;
import edu.vsu.putinpa.questionnairesystem.item.model.MaritalStatus;
import lombok.Data;

import java.util.UUID;

@Data
public class UserDto {
    private UUID id;
    private String username;
    private int age;
    private Gender gender;
    private MaritalStatus maritalStatus;
    private CountryDto country;
}
