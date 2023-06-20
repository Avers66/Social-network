package ru.skillbox.diplom.group35.microservice.notification.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import ru.skillbox.diplom.group35.library.core.repository.BaseRepository;
import ru.skillbox.diplom.group35.microservice.notification.model.Notification;

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
