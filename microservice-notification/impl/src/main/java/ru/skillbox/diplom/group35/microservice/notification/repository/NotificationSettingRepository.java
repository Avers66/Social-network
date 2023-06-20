package ru.skillbox.diplom.group35.microservice.notification.repository;

import org.springframework.stereotype.Repository;
import ru.skillbox.diplom.group35.library.core.repository.BaseRepository;
import ru.skillbox.diplom.group35.microservice.notification.model.NotificationSetting;

import java.util.List;
import java.util.UUID;

/**
 * NotificationSettingRepository
 *
 * @Author Tretyakov Alexandr
 */
@Repository
public interface NotificationSettingRepository extends BaseRepository<NotificationSetting> {
    List<NotificationSetting> getByAccountId(UUID id);
}
