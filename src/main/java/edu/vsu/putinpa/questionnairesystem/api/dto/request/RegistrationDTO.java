package edu.vsu.putinpa.questionnairesystem.api.dto.request;

import edu.vsu.putinpa.questionnairesystem.item.model.Gender;
import edu.vsu.putinpa.questionnairesystem.item.model.MaritalStatus;
import edu.vsu.putinpa.questionnairesystem.validator.CountryExists;
import edu.vsu.putinpa.questionnairesystem.validator.LatinAndDigitsOnly;
import edu.vsu.putinpa.questionnairesystem.validator.UniqueUser;
import edu.vsu.putinpa.questionnairesystem.validator.ValueOfEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record RegistrationDTO(
        @Length(max = 100, message = "Длина имени пользователя не должна превосходить 100 символов")
        @NotNull(message = "Имя пользователя обязательно")
        @LatinAndDigitsOnly
        @UniqueUser
        String username,
        @Length(max = 60, message = "Длина пароля не должна превосходить 60 символов")
        @NotNull(message = "Пароль обязателен")
        String password,
        @Min(value = 1, message = "Возраст должен быть положительным числом")
        @NotNull(message = "Возраст обязателен")
        Integer age,
        @NotNull(message = "Пол обязателен")
        @ValueOfEnum(enumClass = Gender.class)
        String gender,
        @NotNull(message = "Семейное положение обязательно")
        @ValueOfEnum(enumClass = MaritalStatus.class)
        String maritalStatus,
        @CountryExists
        String country
) {}
