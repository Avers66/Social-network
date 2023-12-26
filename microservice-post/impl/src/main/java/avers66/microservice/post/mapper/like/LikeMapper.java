package avers66.microservice.post.mapper.like;

import avers66.microservice.post.dto.like.LikeDto;
import avers66.microservice.post.model.like.Like;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * LikeMapper
 *
 * @author Marat Safagareev
 */

@Mapper(componentModel = "spring")
public interface LikeMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "isDeleted", source = "isDeleted")
    LikeDto convertToDto(Like like);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "isDeleted", constant = "false")
    @Mapping(target = "time", expression = "java(java.time.ZonedDateTime.now())")
    Like convertToEntity(LikeDto likeDto);
}
