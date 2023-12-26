package avers66.microservice.admin_console.impl.service.statistic;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import avers66.library.core.dto.statistic.StatisticPerDateDto;
import avers66.microservice.admin_console.api.dto.statistic.MonthStatisticSearchDto;
import avers66.microservice.admin_console.domain.model.comment.CommentMonthStatistic;
import avers66.microservice.admin_console.domain.model.comment.CommentMonthStatistic_;
import avers66.microservice.admin_console.domain.model.comment.CommentStatistic;
import avers66.microservice.admin_console.domain.model.comment.CountCommentPerHour;
import avers66.microservice.admin_console.impl.mapper.comment.CommentMonthStatisticMapper;
import avers66.microservice.admin_console.impl.mapper.comment.CommentStatisticMapper;
import avers66.microservice.admin_console.impl.mapper.comment.CountCommentPerHourMapper;
import avers66.microservice.admin_console.impl.repository.comment.CommentMonthStatisticRepository;
import avers66.microservice.admin_console.impl.repository.comment.CommentStatisticRepository;
import avers66.microservice.admin_console.impl.repository.comment.CountCommentPerHourRepository;
import avers66.microservice.admin_console.impl.utils.DateTimeUtils;
import avers66.microservice.post.dto.statistic.PostStatisticRequestDto;
import avers66.microservice.post.dto.statistic.StatisticResponseDto;
import avers66.microservice.post.resource.client.StatisticFeignClient;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static avers66.library.core.utils.SpecificationUtil.between;
import static avers66.library.core.utils.SpecificationUtil.getBaseSpecification;

@Service
@RequiredArgsConstructor
public class CommentStatisticService {

    private final CommentMonthStatisticRepository commentMonthStatisticRepository;
    private final CommentStatisticMapper commentStatisticMapper;
    private final CountCommentPerHourMapper countCommentPerHourMapper;
    private final CommentMonthStatisticMapper commentMonthStatisticMapper;
    private final CommentStatisticRepository commentStatisticRepository;
    private final CountCommentPerHourRepository countCommentPerHourRepository;
    private final StatisticFeignClient statisticFeignClient;
    private final DateTimeUtils dateTimeUtils;

    public CommentStatistic findCommentStatistic(PostStatisticRequestDto requestDto) {
        ZonedDateTime startCurrentDay = dateTimeUtils.startDay(ZonedDateTime.now());
        ZonedDateTime startMonth = dateTimeUtils.startMonth(requestDto.getFirstMonth());
        ZonedDateTime endMonth = dateTimeUtils.endMonth(requestDto.getLastMonth());
        Optional<CommentStatistic> foundCommentStatistic = loadCommentStatistic(requestDto.getDate());
        if (foundCommentStatistic.isPresent()) {
            return foundCommentStatistic.get();
        }
        StatisticResponseDto oneMonthStatistic = getRemoteCommentStatistic(startMonth,
                endMonth, requestDto.getDate());
        CommentStatistic commentStatistic = commentStatisticMapper.map(oneMonthStatistic, false);
        if (requestDto.getDate().isBefore(startCurrentDay)) {
            commentStatistic = commentStatisticRepository.save(commentStatistic);
            saveCountPerHour(oneMonthStatistic.getCountPerHours(), commentStatistic);
        }
        return commentStatistic;
    }

    public List<CommentMonthStatistic> findCommentMonthStatistics(PostStatisticRequestDto requestDto) {
        List<CommentMonthStatistic> commentMonthStatistics = new ArrayList<>();
        ZonedDateTime startMonth = dateTimeUtils.startMonth(requestDto.getFirstMonth());
        ZonedDateTime endMonth = dateTimeUtils.endMonth(requestDto.getLastMonth());

        while (!startMonth.isAfter(endMonth)) {
            List<CommentMonthStatistic> monthStatistic = loadCommentMonthStatistic(startMonth,
                    dateTimeUtils.endMonth(startMonth));
            if (monthStatistic.isEmpty()) {
                StatisticResponseDto oneMonthStatistic = getRemoteCommentStatistic(startMonth,
                        dateTimeUtils.endMonth(startMonth), requestDto.getDate());
                monthStatistic = commentMonthStatisticMapper
                        .map(oneMonthStatistic.getCountPerMonth(), false);
                if (!monthStatistic.isEmpty() && monthStatistic.get(0).getDate().isBefore(dateTimeUtils.startMonth(ZonedDateTime.now()))) {
                    saveCommentMonthStatistic(oneMonthStatistic.getCountPerMonth());
                }
            }
            commentMonthStatistics.addAll(monthStatistic);
            startMonth = startMonth.plusMonths(1);
        }
        return commentMonthStatistics;
    }

    private StatisticResponseDto getRemoteCommentStatistic(ZonedDateTime firstMonth,
                                                           ZonedDateTime lastMonth,
                                                           ZonedDateTime dateTime) {
        PostStatisticRequestDto requestDto = new PostStatisticRequestDto();
        requestDto.setDate(dateTime);
        requestDto.setFirstMonth(firstMonth);
        requestDto.setLastMonth(lastMonth);
        return statisticFeignClient.getCommentStatistic(requestDto).getBody();
    }

    public List<CommentMonthStatistic> loadCommentMonthStatistic(ZonedDateTime firstMonth,
                                                              ZonedDateTime lastMonth) {
        MonthStatisticSearchDto monthStatisticSearchDto = new MonthStatisticSearchDto()
                .setFirstMonth(firstMonth).setLastMonth(lastMonth);
        List<CommentMonthStatistic> commentMonthStatistics = commentMonthStatisticRepository
                .findAll(getMonthStatisticSpec(monthStatisticSearchDto));
        return commentMonthStatistics;
    }

    public Optional<CommentStatistic> loadCommentStatistic(ZonedDateTime date) {
        return commentStatisticRepository
                .findByDateBetween(dateTimeUtils.startDay(date), dateTimeUtils.endDay(date))
                .stream()
                .findFirst();
    }

    public List<CommentMonthStatistic> saveCommentMonthStatistic(List<StatisticPerDateDto>
                                                                         statisticPerDateDto) {
        List<CommentMonthStatistic> commentMonthStatistic = commentMonthStatisticMapper
                .map(statisticPerDateDto, false);
        return commentMonthStatisticRepository.saveAll(commentMonthStatistic);
    }

    public List<CountCommentPerHour> saveCountPerHour(List<StatisticPerDateDto> commentCountPerHours,
                                                      CommentStatistic commentStatistic) {
        List<CountCommentPerHour> countCommentPerHours = countCommentPerHourMapper
                .map(commentCountPerHours, commentStatistic, false);
        return countCommentPerHourRepository.saveAll(countCommentPerHours);
    }

    public static Specification<CommentMonthStatistic> getMonthStatisticSpec(MonthStatisticSearchDto monthStatisticSearchDto) {
        return getBaseSpecification(monthStatisticSearchDto)
                .and(between(CommentMonthStatistic_.date, monthStatisticSearchDto.getFirstMonth(),
                        monthStatisticSearchDto.getLastMonth(), true));
    }
}
