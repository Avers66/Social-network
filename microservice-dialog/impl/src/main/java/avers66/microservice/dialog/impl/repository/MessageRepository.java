package avers66.microservice.dialog.impl.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import avers66.library.core.repository.BaseRepository;
import avers66.microservice.dialog.domain.model.Message;

import java.util.Optional;
import java.util.UUID;

/**
 * MessageRepository
 *
 * @author Georgii Vinogradov
 */

@Repository
public interface MessageRepository extends BaseRepository<Message> {

    Optional<Message> findTopByDialogIdOrderByTimeDesc(UUID dialogId);

    @Query("select max(m.kafkaTimestamp) from Message m")
    Long findLastKafkaTimestamp();

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Message m SET m.readStatus = :readStatus WHERE m.dialog.id = :dialogId")
    void updateReadStatusByDialogId(@Param("readStatus") String readStatus, @Param("dialogId") UUID dialogId);

}
