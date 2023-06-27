package ru.skillbox.diplom.group35.microservice.authorization.api.resource;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.skillbox.diplom.group35.microservice.authorization.api.dto.AuthenticateDto;
import ru.skillbox.diplom.group35.microservice.authorization.api.dto.AuthenticateResponseDto;
import ru.skillbox.diplom.group35.microservice.authorization.api.dto.CaptchaDto;
import ru.skillbox.diplom.group35.microservice.authorization.api.dto.NewPasswordDto;
import ru.skillbox.diplom.group35.microservice.authorization.api.dto.PasswordRecoveryDto;
import ru.skillbox.diplom.group35.microservice.authorization.api.dto.RegistrationDto;

/**
 * AuthController
 *
 * @author Mikhail Chechetkin
 */

@Tag(name = "Authorization service", description = "Работа с регистрацией, авторизацией, восстановлением пароля")
@ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
@RequestMapping(value = "/api/v1/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public interface AuthController {

    @PostMapping("/register")
    @Operation(description = "Регистрация нового пользователя")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    void register(RegistrationDto registrationDto);

    @PostMapping("/password/recovery")
    @Operation(description = "Заявка на получение письма со ссылкой для восстановления пароля")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    ResponseEntity<RegistrationDto> getPasswordRecoveryMail(@RequestBody PasswordRecoveryDto passwordRecoveryDto);

    @PostMapping("/password/recovery/{linkId}")
    @Operation(description = "Установка нового пароля")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    ResponseEntity<RegistrationDto> setNewPassword(@PathVariable String linkId,
        @RequestBody NewPasswordDto newPasswordDto);

    @PostMapping("/login")
    @Operation(description = "Авторизация на сайте")
    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content)
    ResponseEntity<AuthenticateResponseDto> login(AuthenticateDto authenticateDto);

    @GetMapping("/captcha")
    @Operation(description = "Получение капчи при регистрации")
    @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content)
    ResponseEntity<CaptchaDto> captcha();

    @PostMapping("/refresh")
    @Operation(description = "Обновление токена системы безопасности")
    @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content)
    ResponseEntity<AuthenticateResponseDto> refresh(AuthenticateResponseDto refreshToken);
}
