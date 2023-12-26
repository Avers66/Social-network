package avers66.microservice.account.impl.mapper;


import org.mapstruct.Mapper;
import avers66.microservice.account.api.dto.RoleDto;
import avers66.microservice.account.domain.model.Role;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    List<RoleDto> map(List<Role> roles);
}
