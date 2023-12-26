package avers66.microservice.account.impl.mapper;

import org.mapstruct.*;
import avers66.library.core.dto.statistic.StatisticPerDateDto;
import avers66.microservice.account.api.dto.AccountDto;
import avers66.microservice.account.api.dto.AccountSecureDto;
import avers66.microservice.account.api.dto.StatPerMonth;
import avers66.microservice.account.domain.model.Account;

@Mapper(componentModel = "spring", uses = {RoleMapper.class, AuthorityMapper.class})
public interface AccountMapper {
    @Mapping(target = "password", ignore = true)
    AccountDto mapToDto(Account account);
    @Mapping(target = "isDeleted", ignore = true)
    AccountSecureDto mapToSecureDto(Account account);

    AccountDto mapToDtoWithPass(Account account);

    Account mapToAccount(AccountDto accountDto);

    @Mapping(source = "date", target = "date", dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    StatisticPerDateDto mapToStatisticPerDateDto(StatPerMonth statPerMonth);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Account updateAccount(AccountDto accountDto, @MappingTarget Account account);
}
