package avers66.microservice.post.repository.tag;

import java.util.List;

import avers66.microservice.post.model.tag.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import avers66.library.core.repository.BaseRepository;

/**
 * TagRepository
 *
 * @author Marat Safagareev
 */
@Repository
public interface TagRepository extends BaseRepository<Tag> {

  @Query("SELECT t FROM Tag t WHERE t.name LIKE ?1% GROUP BY t.id ORDER BY size(t.posts) desc")
  List<Tag> findAdviceTags(String name, Pageable pageable);
}
