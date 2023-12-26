package avers66.microservice.notification.repository;

import avers66.library.core.repository.BaseRepository;
import avers66.microservice.notification.model.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;


import java.util.UUID;

/**
 * NotificationRepository
 *
 * @Author Tretyakov Alexandr
 */

@Repository
public interface NotificationRepository extends BaseRepository<Notification> {
    Page<Notification> getByReceiverIdAndIsRead(UUID receiverId, boolean isRead, Pageable pageable);
    Long countByReceiverIdAndIsRead(UUID receiverId, boolean isRead);

}
