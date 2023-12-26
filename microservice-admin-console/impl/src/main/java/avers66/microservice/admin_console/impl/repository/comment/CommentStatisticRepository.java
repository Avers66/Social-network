package avers66.microservice.admin_console.impl.repository.comment;

import org.springframework.stereotype.Repository;
import avers66.library.core.repository.BaseRepository;
import avers66.microservice.admin_console.domain.model.comment.CommentStatistic;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * CommentStatisticRepository
 *
 * @author Georgii Vinogradov
 */

@Repository
public interface CommentStatisticRepository extends BaseRepository<CommentStatistic> {
    List<CommentStatistic> findByDateBetween(ZonedDateTime startOfDay, ZonedDateTime endOfDay);
}
