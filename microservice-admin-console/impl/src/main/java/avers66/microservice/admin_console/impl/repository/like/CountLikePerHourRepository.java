package avers66.microservice.admin_console.impl.repository.like;

import org.springframework.stereotype.Repository;
import avers66.library.core.repository.BaseRepository;
import avers66.microservice.admin_console.domain.model.like.CountLikePerHour;

@Repository
public interface CountLikePerHourRepository extends BaseRepository<CountLikePerHour> {

}
