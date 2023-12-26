package avers66.microservice.friend.mapper;

import avers66.microservice.friend.dto.FriendShortDto;
import avers66.microservice.friend.model.Friend;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import avers66.microservice.friend.dto.FriendDto;

/**
 * FriendMapper
 *
 * @Author Tretyakov Alexandr
 */
@Mapper(componentModel = "spring")
public interface FriendMapper {

    FriendDto friendToFriendDto(Friend friend);

    Friend friendDtoToFriend(FriendDto friendDto);


    @Mapping(target="idFriend" , source="friend.idFrom") // ToDo delete
    @Mapping(target="friendId" , source="friend.idFrom")
    @Mapping(target="id" , source="friend.idFrom")
    FriendShortDto friendToFriendShortDto(Friend friend);
}
