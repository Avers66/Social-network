package avers66.microservice.post.mapper.tag;

import java.util.List;
import java.util.Set;

import avers66.microservice.post.dto.tag.TagDto;
import avers66.microservice.post.model.tag.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * TagMapper
 *
 * @author Marat Safagareev
 */
@Mapper(componentModel = "spring")
public interface TagMapper {

  TagDto toTagDto(Tag tag);

  List<TagDto> toTagDtoList(List<Tag> tags);

  Set<TagDto> toTagDtos(Set<Tag> tags);

  Set<Tag> toTags(Set<TagDto> tagDtos);

  @Mapping(source = "isDeleted", target = "isDeleted", defaultValue = "false")
  Tag toTag(TagDto tagDto);
}
