package avers66.microservice.post.mapper.post;

import avers66.microservice.post.dto.post.PostDto;
import avers66.microservice.post.mapper.like.LikeMapper;
import avers66.microservice.post.model.post.Post;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import avers66.library.core.dto.statistic.StatisticPerDateDto;
import avers66.microservice.post.dto.statistic.StatisticPerDate;
import avers66.microservice.post.mapper.tag.TagMapper;

@Mapper(componentModel = "spring", uses = {TagMapper.class, LikeMapper.class})
public interface PostMapper {

  PostDto toPostDto(Post post);

  @Mapping(source = "isDeleted", target = "isDeleted", defaultValue = "false")
  @Mapping(target = "time", expression = "java((postDto.getPublishDate() == null) ? java.time.ZonedDateTime.now() : postDto.getPublishDate())")
  @Mapping(source = "commentsCount", target = "commentsCount", defaultValue = "0")
  @Mapping(source = "likeAmount", target = "likeAmount", defaultValue = "0")
  @Mapping(target = "type", expression = "java((postDto.getPublishDate() != null) ? " +
                                          "avers66.microservice.post.model.post.PostType.QUEUED : " +
                                          "avers66.microservice.post.model.post.PostType.POSTED)")
  @Mapping(source = "isBlocked", target = "isBlocked", defaultValue = "false")
  @Mapping(source = "myLike", target = "myLike", defaultValue = "false")
  Post toPost(PostDto postDto);

  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  Post updatePostFromDto(PostDto postDto, @MappingTarget Post post);

  @Mapping(source = "date", target = "date", dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
  StatisticPerDateDto convertToStatisticPerDateDto(StatisticPerDate statPerDate);
}
