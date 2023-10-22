package edu.vsu.putinpa.questionnairesystem.dto.request;

import edu.vsu.putinpa.questionnairesystem.validator.UniqueAuthor;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record AuthorRegistrationDTO(
        @Length(max = 100, message = "Длина имени пользователя не должна превосходить 100 символов")
        @NotNull(message = "Имя пользователя обязательно")
        @UniqueAuthor
        String username,
        @Length(max = 60, message = "Длина пароля не должна превосходить 60 символов")
        @NotNull(message = "Пароль обязателен")
        String password
) {}
