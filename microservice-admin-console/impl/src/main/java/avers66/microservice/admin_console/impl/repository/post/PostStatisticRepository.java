package avers66.microservice.admin_console.impl.repository.post;

import org.springframework.stereotype.Repository;
import avers66.library.core.repository.BaseRepository;
import avers66.microservice.admin_console.domain.model.post.PostStatistic;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * PostStatisticRepository
 *
 * @author Georgii Vinogradov
 */

@Repository
public interface PostStatisticRepository extends BaseRepository<PostStatistic> {
    List<PostStatistic> findByDateBetween(ZonedDateTime startOfDay, ZonedDateTime endOfDay);
}
