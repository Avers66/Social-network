package avers66.microservice.account.api.dto;

import lombok.Getter;
import lombok.Setter;
import avers66.library.core.dto.base.BaseDto;

/**
 * UserDto
 *
 * @author Georgii Vinogradov
 */
@Getter
@Setter
public class UserDto extends BaseDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
