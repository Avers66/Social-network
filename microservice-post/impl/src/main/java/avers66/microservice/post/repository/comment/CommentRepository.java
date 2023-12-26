package avers66.microservice.post.repository.comment;

import avers66.microservice.post.model.comment.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import avers66.library.core.repository.BaseRepository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface CommentRepository extends BaseRepository<Comment> {

    Page<Comment> findAll(Specification<Comment> specification, Pageable page);
    List<Comment> findAllByTimeBefore(ZonedDateTime time);
}
