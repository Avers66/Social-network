package ru.skillbox.diplom.group35.microservice.notification.mapper;

import org.mapstruct.Mapper;
import ru.skillbox.diplom.group35.library.core.dto.streaming.EventNotificationDto;
import ru.skillbox.diplom.group35.microservice.notification.dto.NotificationDto;
import ru.skillbox.diplom.group35.microservice.notification.model.Notification;

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
