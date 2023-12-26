package avers66.microservice.admin_console.impl.mapper.like;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import avers66.library.core.dto.statistic.StatisticPerDateDto;
import avers66.microservice.admin_console.domain.model.like.CountLikePerHour;
import avers66.microservice.admin_console.domain.model.like.LikeStatistic;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CountLikePerHourMapper {
    @Mapping(target = "likeStatistic", ignore = true)
    CountLikePerHour map(StatisticPerDateDto statisticPerDateDto);

    List<StatisticPerDateDto> map(List<CountLikePerHour> countLikePerHours);

    @Mapping(target = "likeStatistic", ignore = true)
    default List<CountLikePerHour> map(List<StatisticPerDateDto> statisticPerDateDtos,
                                       LikeStatistic likeStatistic, Boolean isDeleted) {
        return statisticPerDateDtos.stream().map(m ->  {
            CountLikePerHour countLikePerHour = this.map(m);
            countLikePerHour.setLikeStatistic(likeStatistic);
            countLikePerHour.setIsDeleted(isDeleted);
            return countLikePerHour;
        }).collect(Collectors.toList());
    }

}
