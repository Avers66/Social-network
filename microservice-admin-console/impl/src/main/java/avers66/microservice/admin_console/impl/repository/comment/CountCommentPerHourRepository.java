package avers66.microservice.admin_console.impl.repository.comment;

import org.springframework.stereotype.Repository;
import avers66.library.core.repository.BaseRepository;
import avers66.microservice.admin_console.domain.model.comment.CountCommentPerHour;

@Repository
public interface CountCommentPerHourRepository extends BaseRepository<CountCommentPerHour> {

}
