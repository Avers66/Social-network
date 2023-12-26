package avers66.microservice.account.impl.mapper;

import org.mapstruct.Mapper;
import avers66.microservice.account.api.dto.AuthorityDto;
import avers66.microservice.account.domain.model.Authority;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorityMapper {

    List<AuthorityDto> map(List<Authority> roles);
}
