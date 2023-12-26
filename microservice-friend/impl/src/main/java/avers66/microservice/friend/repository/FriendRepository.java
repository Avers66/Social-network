package avers66.microservice.friend.repository;

import avers66.library.core.repository.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import avers66.microservice.friend.model.Friend;


import java.util.List;
import java.util.UUID;

/**
 * FriendRepository
 *
 * @author Grigory Dyakonov
 */

@Repository
public interface FriendRepository extends BaseRepository<Friend> {

    @Query("select f from Friend f where (f.idTo = :myId and f.idFrom = :idTo) or (f.idTo = :idTo and f.idFrom = :myId)")
    List<Friend> findFriendPair(@Param("myId") UUID myId, @Param("idTo") UUID idTo);

    //void deleteAll(List<Friend> listFriend);
}
