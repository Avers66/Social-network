package avers66.microservice.account.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AuthorityDto {
    private UUID id;
    private String authority;
}
