package avers66.microservice.post.dto.tag;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import avers66.library.core.dto.base.BaseSearchDto;

/**
 * TagSearchDto
 *
 * @author Marat Safagareev
 */
@Getter
@Setter
@Schema(description = "DTO тега для поиска")
public class TagSearchDto extends BaseSearchDto {

  @Schema(description = "Имя тега для поиска")
  private String name;
}
