package avers66.microservice.admin_console.impl.mapper.account;

import org.mapstruct.*;
import avers66.microservice.account.api.dto.AccountStatisticResponseDto;
import avers66.microservice.admin_console.domain.model.account.AccountStatistic;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountStatisticMapper {
    AccountStatistic map(AccountStatisticResponseDto responseDto, Boolean isDeleted);
}
