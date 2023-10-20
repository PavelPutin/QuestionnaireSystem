package edu.vsu.putinpa.questionnairesystem.dto.request;

import org.hibernate.validator.constraints.Length;

public record AuthorRegistrationDTO(
        @Length(max = 100, message = "Длина имени пользователя не должна превосходить 100 символов")
        String username,
        @Length(max = 60, message = "Длина пароля не должна превосходить 60 символов")
        String password) {
}
