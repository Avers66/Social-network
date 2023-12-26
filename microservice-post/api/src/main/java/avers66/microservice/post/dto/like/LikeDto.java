package avers66.microservice.post.dto.like;

import java.time.ZonedDateTime;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import avers66.library.core.dto.base.BaseDto;
import avers66.microservice.post.model.like.LikeType;

/**
 * LikeDto
 *
 * @author Marat Safagareev
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO лайка")
public class LikeDto extends BaseDto {

  @Schema(description = "ID автора комментария")
  private UUID authorId;

  @Schema(description = "Дата создания лайка")
  private ZonedDateTime time;

  @Schema(description = "ID поста или комментария, к которому принадлежит лайк")
  private UUID itemId;

  @Schema(description = "Тип лайка: POST - лайк на пост, COMMENT- лайк на комментарий")
  private LikeType type;

  @Schema(description = "Тип реакции лайка")
  private String reactionType;
}
