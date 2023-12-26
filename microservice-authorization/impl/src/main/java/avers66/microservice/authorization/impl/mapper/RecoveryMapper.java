package avers66.microservice.authorization.impl.mapper;

import org.mapstruct.Mapper;
import avers66.microservice.authorization.api.dto.PasswordResetTokenDto;
import avers66.microservice.authorization.domain.model.PasswordResetToken;

/**
 * RecoveryMapper
 *
 * @author Marat Safagareev
 */
@Mapper(componentModel = "spring")
public interface RecoveryMapper {

  PasswordResetTokenDto toResetDto(PasswordResetToken passwordResetToken);
}