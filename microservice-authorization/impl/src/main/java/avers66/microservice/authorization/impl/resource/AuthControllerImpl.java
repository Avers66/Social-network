package avers66.microservice.authorization.impl.resource;

import avers66.microservice.authorization.impl.service.AuthenticationService;
import avers66.microservice.authorization.impl.service.CaptchaService;
import avers66.microservice.authorization.impl.service.RecoveryService;
import avers66.microservice.authorization.impl.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import avers66.library.core.annotation.EnableExceptionHandler;
import avers66.library.core.annotation.EnableSwagger;
import avers66.microservice.authorization.api.dto.AuthenticateDto;
import avers66.microservice.authorization.api.dto.AuthenticateResponseDto;
import avers66.microservice.authorization.api.dto.CaptchaDto;
import avers66.microservice.authorization.api.dto.NewPasswordDto;
import avers66.microservice.authorization.api.dto.PasswordRecoveryDto;
import avers66.microservice.authorization.api.dto.RegistrationDto;
import avers66.microservice.authorization.api.resource.AuthController;

/**
 * AuthorizationController
 *
 * @author Mikhail Chechetkin
 */
@Slf4j
@EnableSwagger
@RestController
@EnableExceptionHandler
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final RegistrationService registrationService;
    private final AuthenticationService authenticationService;
    private final CaptchaService captchaService;
    private final RecoveryService recoveryService;

    @Override
    public void register(@RequestBody RegistrationDto registrationDto) {
        log.info("Registration new account, Email:{}", registrationDto.getEmail());
        registrationService.create(registrationDto);
    }

    @Override
    public ResponseEntity<RegistrationDto> getPasswordRecoveryMail(PasswordRecoveryDto passwordRecoveryDto) {
        log.info("Received request for password recovery email : {}", passwordRecoveryDto);
        recoveryService.getPasswordRecoveryMail(passwordRecoveryDto);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<RegistrationDto> setNewPassword(String linkId, NewPasswordDto newPasswordDto) {
        log.info("Received setting new password request with token: {}", linkId);
        recoveryService.setNewPassword(linkId, newPasswordDto);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<AuthenticateResponseDto> login(@RequestBody AuthenticateDto authenticateDto) {
        log.info("Get authentication by email, Email:{}", authenticateDto.getEmail());
        return ResponseEntity.ok(authenticationService.getAuthenticationResponse(authenticateDto));
    }

    @Override
    public ResponseEntity<CaptchaDto> captcha() {
        log.info("Getting captcha");
        return ResponseEntity.ok(captchaService.getCaptcha());
    }

    @Override
    public ResponseEntity<AuthenticateResponseDto> refresh(@RequestBody AuthenticateResponseDto refreshToken) {
        log.info("refresh token");
        return ResponseEntity.ok(authenticationService.refreshToken(refreshToken.getRefreshToken()));
    }
}
