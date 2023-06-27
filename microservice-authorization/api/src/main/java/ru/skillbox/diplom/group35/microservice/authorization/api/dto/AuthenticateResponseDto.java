package ru.skillbox.diplom.group35.microservice.authorization.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * AuthenticateResponseDto
 *
 * @author Mikhail Chechetkin
 */

@Data
@Schema(description = "DTO ответа на аутентификацию")
public class AuthenticateResponseDto{
    @Schema(description = "Токен для доступа")
    private String accessToken;
    @Schema(description = "Токен для обновления")
    private String refreshToken;
}
