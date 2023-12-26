package avers66.microservice.admin_console.impl.mapper.post;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import avers66.microservice.admin_console.domain.model.post.PostStatistic;
import avers66.microservice.post.dto.statistic.StatisticResponseDto;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostStatisticMapper {
    @Mapping(target = "countPostPerHour", source = "responseDto.countPerMonth")
    PostStatistic map(StatisticResponseDto responseDto, Boolean isDeleted);
}
