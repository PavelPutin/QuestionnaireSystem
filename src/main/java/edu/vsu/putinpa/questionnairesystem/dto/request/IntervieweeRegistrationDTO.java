package edu.vsu.putinpa.questionnairesystem.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import edu.vsu.putinpa.questionnairesystem.model.Interviewee;
import edu.vsu.putinpa.questionnairesystem.validator.CountryExists;
import edu.vsu.putinpa.questionnairesystem.validator.UniqueInterviewee;
import edu.vsu.putinpa.questionnairesystem.validator.ValueOfEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record IntervieweeRegistrationDTO(
        @Length(max = 100, message = "Длина имени пользователя не должна превосходить 100 символов")
        @NotNull(message = "Имя пользователя обязательно")
        @UniqueInterviewee
        String username,
        @Length(max = 60, message = "Длина пароля не должна превосходить 60 символов")
        @NotNull(message = "Пароль обязателен")
        String password,
        @Min(value = 1, message = "Возраст должен быть положительным числом")
        @NotNull(message = "Возраст обязателен")
        Integer age,
        @NotNull(message = "Пол обязателен")
        @ValueOfEnum(enumClass = Interviewee.Gender.class)
        String gender,
        @NotNull(message = "Семейное положение обязательно")
        @ValueOfEnum(enumClass = Interviewee.MaritalStatus.class)
        String maritalStatus,
        @CountryExists
        String country
) {}
