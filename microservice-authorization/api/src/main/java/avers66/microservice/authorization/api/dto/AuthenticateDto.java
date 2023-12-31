package avers66.microservice.authorization.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * AuthenticateDto
 *
 * @author Mikhail Chechetkin
 */

@Data
@Schema(description = "DTO аутентификации")
public class AuthenticateDto {
    @Schema(description = "Электронная почта пользователя")
    private String email;
    @Schema(description = "Пароль пользователя")
    private String password;
}
