package edu.vsu.putinpa.questionnairesystem.api.dto.request;

import edu.vsu.putinpa.questionnairesystem.item.model.Country;
import edu.vsu.putinpa.questionnairesystem.item.model.Gender;
import edu.vsu.putinpa.questionnairesystem.item.model.MaritalStatus;
import edu.vsu.putinpa.questionnairesystem.validator.CountryExists;
import edu.vsu.putinpa.questionnairesystem.validator.ValueOfEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class UserUpdateDTO {
    @Min(value = 1, message = "Возраст должен быть положительным числом")
    @NotNull(message = "Возраст обязателен")
    private int age;

    @NotNull(message = "Пол обязателен")
    @ValueOfEnum(enumClass = Gender.class)
    private Gender gender;

    @NotNull(message = "Семейное положение обязательно")
    @ValueOfEnum(enumClass = MaritalStatus.class)
    private MaritalStatus maritalStatus;

    @CountryExists
    private Country country;
}
