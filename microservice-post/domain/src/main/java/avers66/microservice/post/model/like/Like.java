package avers66.microservice.post.model.like;

import java.time.ZonedDateTime;
import java.util.UUID;
import javax.persistence.*;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import avers66.library.core.model.base.BaseEntity;
//import avers66.microservice.post.model.comment.CommentType;


/**
 * Like
 *
 * @author Marat Safagareev
 */

@Getter
@Setter
@Entity
@Table(name = "like")
@RequiredArgsConstructor
public class Like extends BaseEntity {

  @Column(name = "author_id", columnDefinition = "uuid", nullable = false)
  private UUID authorId;

  @Column(name = "time", columnDefinition = "TIMESTAMP WITH TIME ZONE", nullable = false)
  private ZonedDateTime time;

  @Column(name = "item_id", columnDefinition = "uuid", nullable = false)
  private UUID itemId;

  @Enumerated(EnumType.STRING)
  private LikeType type;

  @Column(name = "reaction_type", columnDefinition = "varchar(255)")
  private String reactionType;
}
