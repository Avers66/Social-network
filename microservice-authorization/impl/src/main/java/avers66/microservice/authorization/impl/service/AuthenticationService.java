package avers66.microservice.authorization.impl.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import avers66.library.core.annotation.JwtProvider;
import avers66.library.core.exception.UnauthorizedException;
import avers66.library.core.security.config.SecurityConfig;
import avers66.library.core.security.config.TechnicalUserConfig;
import avers66.library.core.security.jwt.JwtTokenProvider;
import avers66.library.core.utils.SecurityUtil;
import avers66.microservice.account.api.client.AccountFeignClient;
import avers66.microservice.account.api.dto.AccountSecureDto;
import avers66.microservice.account.domain.model.Authority;
import avers66.microservice.account.domain.model.Role;
import avers66.microservice.authorization.api.dto.AuthenticateDto;
import avers66.microservice.authorization.api.dto.AuthenticateResponseDto;
import avers66.microservice.authorization.api.dto.KafkaDto;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@JwtProvider
@RequiredArgsConstructor
public class AuthenticationService {
    private final JwtTokenProvider jwtTokenProvider;
    private final AccountFeignClient accountFeignClient;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TechnicalUserConfig technicalUserConfig;
    private final  SecurityConfig securityConfig;
    private final SecurityUtil securityUtil;
    private final KafkaProducerService kafkaProducerService;




    public AuthenticateResponseDto getAuthenticationResponse(AuthenticateDto authenticateDto) throws UnauthorizedException {
        AuthenticateResponseDto authenticateResponseDto = new AuthenticateResponseDto();
        KafkaDto kafkaDto = new KafkaDto(authenticateDto.getEmail(), ZonedDateTime.now());
        ResponseEntity<AccountSecureDto> responseEntity = technicalUserConfig.executeByTechnicalUser(
                ()->accountFeignClient.getByEmail(authenticateDto.getEmail()));
        AccountSecureDto accountSecureDto = responseEntity.getBody();
        if (accountSecureDto != null
                && bCryptPasswordEncoder.matches(authenticateDto.getPassword(), accountSecureDto.getPassword())) {
            List<String> roles = accountSecureDto.getRoles()
                    .stream()
                    .map(Role::getRole)
                    .collect(Collectors.toList());
            List<String> authorities =  accountSecureDto.getAuthorities()
                    .stream()
                    .map(Authority::getAuthority)
                    .collect(Collectors.toList());
            roles.addAll(authorities);
            String jwtToken = jwtTokenProvider.createToken(
                    accountSecureDto.getId(),
                    accountSecureDto.getEmail(),
                   roles);
            String refreshJwtToken = jwtTokenProvider.refreshToken(accountSecureDto.getId(), accountSecureDto.getEmail());
            authenticateResponseDto.setAccessToken(jwtToken);
            authenticateResponseDto.setRefreshToken(refreshJwtToken);
            kafkaDto.setSuccessful(true);
            kafkaProducerService.sendAuthentication(kafkaDto);
        }
        else {
            String errorMessage = "Incorrect email or password";
            kafkaDto.setSuccessful(false);
            kafkaDto.setError(errorMessage);
            kafkaProducerService.sendAuthentication(kafkaDto);
            throw new UnauthorizedException(errorMessage);
        }
        return authenticateResponseDto;
    }

    public AuthenticateResponseDto refreshToken(String refreshToken) {
        UUID id = UUID.fromString(securityConfig.jwtDecoder().decode(refreshToken).getClaims().get("id").toString());
        String email= (String) securityConfig.jwtDecoder().decode(refreshToken).getClaims().get("email");
        if (!securityUtil.getAccountDetails().getId().equals(id)
                && !securityUtil.getAccountDetails().getEmail().equals(email)){
            throw new UnauthorizedException("invalid refresh token");
        }
        AuthenticateResponseDto authenticateResponseDto = new AuthenticateResponseDto();
        authenticateResponseDto.setRefreshToken(jwtTokenProvider.refreshToken(id, email));
        authenticateResponseDto.setAccessToken(jwtTokenProvider.createToken(id, email, securityUtil.getAccountDetails().getRoles()));
       return authenticateResponseDto;
    }
}
