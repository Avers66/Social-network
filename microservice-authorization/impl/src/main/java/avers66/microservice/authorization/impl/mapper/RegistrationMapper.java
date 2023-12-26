package avers66.microservice.authorization.impl.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import avers66.microservice.account.api.dto.AccountDto;
import avers66.microservice.authorization.api.dto.RegistrationDto;

/**
 * RegistrationMapper
 *
 * @author Mikhail Chechetkin
 */

@Mapper
public interface RegistrationMapper {

    @Mapping(source = "password1", target = "password")
    AccountDto mapToAccount(RegistrationDto registrationDto);


}
