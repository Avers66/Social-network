package avers66.microservice.account.api.dto;


import lombok.Getter;
import lombok.Setter;
import avers66.library.core.dto.base.BaseDto;
import avers66.microservice.account.domain.model.Authority;
import avers66.microservice.account.domain.model.Role;

import java.util.List;

@Getter
@Setter
public class AccountSecureDto extends BaseDto {
    private String firstName;
    private String email;
    private String password;
    private List<Role> roles;
    private List<Authority> authorities;
}
