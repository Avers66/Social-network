package avers66.microservice.authorization.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * NewPasswordDto
 *
 * @author Mikhail Chechetkin
 */

@Data
@Schema(description = "DTO для установки нового пароля")
public class NewPasswordDto {
    @Schema(description = "Новый пароль")
    private String password;
}
