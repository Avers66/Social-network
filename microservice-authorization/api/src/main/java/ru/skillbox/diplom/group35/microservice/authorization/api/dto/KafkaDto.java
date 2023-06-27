package ru.skillbox.diplom.group35.microservice.authorization.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@Schema(description = "DTO для отправки статистики авторизации")
public class KafkaDto {
    @Schema(description = "Электронная почта пользователя")
    private String email;
    @Schema(description = "Дата авторизации")
    private ZonedDateTime registrationDate;
    @Schema(description = "Была ли авторизация успешна")
    private boolean isSuccessful;
    @Schema(description = "Описание ошибки в случае её возникновения")
    private String error;

    public KafkaDto(String email, ZonedDateTime registrationDate) {
        this.email = email;
        this.registrationDate = registrationDate;
    }
}
