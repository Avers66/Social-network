package avers66.microservice.post.resource.post;

import java.util.List;

import avers66.microservice.post.dto.tag.TagDto;
import avers66.microservice.post.dto.tag.TagSearchDto;
import avers66.microservice.post.service.tag.TagService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import avers66.library.core.annotation.EnableExceptionHandler;

/**
 * TagControllerImpl
 *
 * @author Marat Safagareev
 */
@Slf4j
@RestController
@EnableExceptionHandler
@RequiredArgsConstructor
public class TagControllerImpl implements TagController {

  private final TagService tagService;

  @Override
  public ResponseEntity<List<TagDto>> getAdviceTags(TagSearchDto tagSearchDto) {
    log.info("getAdviceTags(): tagSearchDto:{}", tagSearchDto);
    return ResponseEntity.ok(tagService.getAdviceTags(tagSearchDto));
  }
}
