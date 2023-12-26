package avers66.microservice.post.service.post;

import java.time.ZonedDateTime;
import java.util.List;

import avers66.microservice.post.model.post.Post;
import avers66.microservice.post.model.post.PostType;
import avers66.microservice.post.repository.post.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * DelayedPostService
 *
 * @author Marat Safagareev
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DelayedPostService {

  private final PostRepository postRepository;

  public void publishPost() {
    List<Post> postsToPublish = postRepository
        .findAllByTypeAndPublishDateBefore(PostType.QUEUED, ZonedDateTime.now());
    postsToPublish.forEach(post -> {
      post.setTime(post.getPublishDate());
      post.setPublishDate(null);
      post.setType(PostType.POSTED);
      postRepository.save(post);
    });
  }
}
