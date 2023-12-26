package avers66.microservice.authorization.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * PasswordRecoveryDto
 *
 * @author Mikhail Chechetkin
 */

@Data
@Schema(description = "DTO для заявки на смену пароля")
public class PasswordRecoveryDto {
    @Schema(description = "Электронная почта пользователя")
    private String email;
}
