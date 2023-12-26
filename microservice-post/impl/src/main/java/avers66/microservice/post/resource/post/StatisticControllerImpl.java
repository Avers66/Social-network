package avers66.microservice.post.resource.post;

import avers66.microservice.post.dto.statistic.PostStatisticRequestDto;
import avers66.microservice.post.dto.statistic.StatisticResponseDto;
import avers66.microservice.post.service.statistic.StatisticService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import avers66.library.core.annotation.EnableExceptionHandler;

/**
 * StatisticControllerImpl
 *
 * @author Vladimir Polochanin
 */
@Slf4j
@RestController
@EnableExceptionHandler
@RequiredArgsConstructor
public class StatisticControllerImpl implements StatisticController {

    private final StatisticService statisticService;

    @Override
    public ResponseEntity<StatisticResponseDto> getPostStatistic(PostStatisticRequestDto requestDto) {
        return ResponseEntity.ok(statisticService.getPostStatistic(requestDto));
    }

    @Override
    public ResponseEntity<StatisticResponseDto> getCommentStatistic(PostStatisticRequestDto requestDto) {
        return ResponseEntity.ok(statisticService.getCommentStatistic(requestDto));
    }

    @Override
    public ResponseEntity<StatisticResponseDto> getLikeStatistic(PostStatisticRequestDto requestDto) {
        return ResponseEntity.ok(statisticService.getLikeStatistic(requestDto));
    }
}
