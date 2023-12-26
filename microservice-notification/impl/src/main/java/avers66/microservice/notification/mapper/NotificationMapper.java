package avers66.microservice.notification.mapper;

import avers66.microservice.notification.dto.NotificationDto;
import avers66.microservice.notification.model.Notification;
import org.mapstruct.Mapper;
import avers66.library.core.dto.streaming.EventNotificationDto;

/**
 * NotificationMapper
 *
 * @Author Tretyakov Alexandr
 */

@Mapper(componentModel = "spring")
public interface NotificationMapper {
    NotificationDto notificationToDto(Notification notification);
    Notification eventToNotification(EventNotificationDto eventNotificationDto);
}
