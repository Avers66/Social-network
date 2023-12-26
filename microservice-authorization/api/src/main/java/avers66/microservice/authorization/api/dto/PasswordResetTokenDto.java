package avers66.microservice.authorization.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.ZonedDateTime;
import java.util.UUID;
import lombok.Data;

/**
 * PasswordResetTokenDto
 *
 * @author Marat Safagareev
 */
@Data
@Schema(description = "DTO токена на смену пароля")
public class PasswordResetTokenDto {
  @Schema(description = "UUID токена")
  UUID id;
  @Schema(description = "Электронная почта пользователя")
  String email;
  @Schema(description = "Имя пользователя")
  String firstName;
  @Schema(description = "Количество минут до истечения срока действия токена")
  int expiration;
  @Schema(description = "Дата и время истечения срока действия токена")
  ZonedDateTime expirationTime;
}
