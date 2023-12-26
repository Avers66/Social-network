package avers66.microservice.admin_console.impl.mapper.post;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import avers66.microservice.admin_console.domain.model.post.PostMonthStatistic;
import avers66.microservice.admin_console.domain.model.post.PostStatistic;
import avers66.microservice.post.dto.statistic.PostStatisticRequestDto;
import avers66.microservice.post.dto.statistic.StatisticResponseDto;

import java.util.List;

/**
 * PostResponseMapper
 *
 * @author Georgii Vinogradov
 */

@Mapper(componentModel = "spring")
public abstract class PostResponseMapper {

    @Autowired
    private CountPostPerHourMapper countPostPerHourMapper;

    @Autowired
    private PostMonthStatisticMapper postMonthStatisticMapper;

    public StatisticResponseDto map(PostStatisticRequestDto requestDto, List<PostMonthStatistic> postMonthStatistics, PostStatistic postStatistic) {
        StatisticResponseDto responseDto = new StatisticResponseDto();
        responseDto.setDate(requestDto.getDate());
        responseDto.setCount(postStatistic.getCount());
        responseDto.setCountPerHours(countPostPerHourMapper.map(postStatistic.getCountPostPerHour()));
        responseDto.setCountPerMonth(postMonthStatisticMapper.map(postMonthStatistics));
        return responseDto;
    }

}
