package avers66.microservice.admin_console.impl.repository.like;

import org.springframework.stereotype.Repository;
import avers66.library.core.repository.BaseRepository;
import avers66.microservice.admin_console.domain.model.like.LikeStatistic;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * LikeStatisticRepository
 *
 * @author Georgii Vinogradov
 */

@Repository
public interface LikeStatisticRepository extends BaseRepository<LikeStatistic> {
    List<LikeStatistic> findByDateBetween(ZonedDateTime startOfDay, ZonedDateTime endOfDay);
}
