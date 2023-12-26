package avers66.microservice.admin_console.impl.repository.post;

import org.springframework.stereotype.Repository;
import avers66.library.core.repository.BaseRepository;
import avers66.microservice.admin_console.domain.model.post.CountPostPerHour;

@Repository
public interface CountPostPerHourRepository extends BaseRepository<CountPostPerHour> {

}
