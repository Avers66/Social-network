package avers66.microservice.post.dto.like;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Reaction
 *
 * @author Vladimir Polochanin
 */
@Data
@AllArgsConstructor
public class ReactionDto {
    private String reactionType;
    private Long count;
}
