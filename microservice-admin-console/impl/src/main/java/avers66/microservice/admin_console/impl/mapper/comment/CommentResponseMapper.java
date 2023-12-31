package avers66.microservice.admin_console.impl.mapper.comment;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import avers66.microservice.admin_console.domain.model.comment.CommentMonthStatistic;
import avers66.microservice.admin_console.domain.model.comment.CommentStatistic;
import avers66.microservice.post.dto.statistic.PostStatisticRequestDto;
import avers66.microservice.post.dto.statistic.StatisticResponseDto;

import java.util.List;

/**
 * CommentResponseMapper
 *
 * @author Georgii Vinogradov
 */

@Mapper(componentModel = "spring")
public abstract class CommentResponseMapper {
    @Autowired
    private CountCommentPerHourMapper countCommentPerHourMapper;
    @Autowired
    private CommentMonthStatisticMapper commentMonthStatisticMapper;

    public StatisticResponseDto map(PostStatisticRequestDto requestDto,
                                    List<CommentMonthStatistic> commentMonthStatistics,
                                    CommentStatistic commentStatistic) {
        StatisticResponseDto responseDto = new StatisticResponseDto();
        responseDto.setDate(requestDto.getDate());
        responseDto.setCount(commentStatistic.getCount());
        responseDto.setCountPerHours(countCommentPerHourMapper.map(commentStatistic.getCountCommentPerHour()));
        responseDto.setCountPerMonth(commentMonthStatisticMapper.map(commentMonthStatistics));
        return responseDto;
    }

}
