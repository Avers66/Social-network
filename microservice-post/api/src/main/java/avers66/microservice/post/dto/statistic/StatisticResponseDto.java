package avers66.microservice.post.dto.statistic;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import avers66.library.core.dto.statistic.StatisticPerDateDto;

import java.util.List;

/**
 * PostStatisticResponseDto
 *
 * @author Vladimir Polochanin
 */

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "DTO статистики постов, лайков, комментариев")
public class StatisticResponseDto extends StatisticPerDateDto {

    @Schema(description = "Количество объектов по часам за промежуток времени")
    private List<StatisticPerDateDto> countPerHours;

    @Schema(description = "Количество объектов по месяцам за промежуток времени")
    private List<StatisticPerDateDto> countPerMonth;
}

