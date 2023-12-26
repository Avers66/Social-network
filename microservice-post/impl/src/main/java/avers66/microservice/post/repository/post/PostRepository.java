package avers66.microservice.post.repository.post;

import java.time.ZonedDateTime;
import java.util.List;

import avers66.microservice.post.model.post.Post;
import org.springframework.stereotype.Repository;
import avers66.library.core.repository.BaseRepository;
import avers66.microservice.post.model.post.PostType;

@Repository
public interface PostRepository extends BaseRepository<Post> {
  List<Post> findAllByTypeAndPublishDateBefore(PostType postType, ZonedDateTime time);

  List<Post> findAllByTypeAndTimeBefore(PostType postType, ZonedDateTime time);
}
