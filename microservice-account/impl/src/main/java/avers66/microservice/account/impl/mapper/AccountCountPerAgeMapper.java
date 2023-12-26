package avers66.microservice.account.impl.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import avers66.microservice.account.api.dto.AccountCountPerAge;
import avers66.microservice.account.api.dto.IAccountCountPerAge;

import java.util.List;

@Mapper
public interface AccountCountPerAgeMapper {
    @Mapping(source = "age", target = "age")
    @Mapping(source = "count", target = "count")
    AccountCountPerAge map(IAccountCountPerAge countPerAge);

    List<AccountCountPerAge> map(List<IAccountCountPerAge> countPerAgeList);
}
