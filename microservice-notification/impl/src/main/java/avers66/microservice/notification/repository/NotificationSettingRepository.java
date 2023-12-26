package avers66.microservice.notification.repository;

import avers66.microservice.notification.model.NotificationSetting;
import org.springframework.stereotype.Repository;
import avers66.library.core.repository.BaseRepository;

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
