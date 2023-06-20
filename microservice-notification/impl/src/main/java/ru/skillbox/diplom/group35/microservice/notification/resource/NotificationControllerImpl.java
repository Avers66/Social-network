package ru.skillbox.diplom.group35.microservice.notification.resource;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.skillbox.diplom.group35.library.core.dto.streaming.EventNotificationDto;
import ru.skillbox.diplom.group35.microservice.notification.dto.*;
import ru.skillbox.diplom.group35.microservice.notification.service.NotificationService;


import java.util.UUID;

/**
 * NotificationControllerImpl
 *
 * @Author Tretyakov Alexandr
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class NotificationControllerImpl implements NotificationController {

    private final NotificationService notificationService;


    @Override
    public ResponseEntity<NotificationSettingDto> getSetting() {
        log.info("/api/v1/notifications/settings принят запрос GET ");
        return ResponseEntity.ok(notificationService.getSetting());
    }

    @Override
    public void updateSetting(NotificationUpdateDto dto) {
        log.info("/api/v1/notifications/settings принят запрос PUT ");
        notificationService.updateSetting(dto);
    }

    @Override
    public ResponseEntity<Boolean> createSetting(UUID id) {
        log.info("/api/v1/notifications/settings{id} принят запрос POST ");
        return notificationService.createSetting(id);
    }

    @Override
    public void addEvent(EventNotificationDto event) {
        log.info("/api/v1/notifications/add принят запрос POST " + event.toString());
        notificationService.addEvent(event);
    }

    @Override
    public ResponseEntity<Page<NotificationsDto>> getNotifications(Pageable page) {
        log.info("/api/v1/notifications принят запрос на получение нотификаций ");
        return ResponseEntity.ok(notificationService.getNotifications(page));
    }

    @Override
    public void setIsRead() {
        log.info("/api/v1/notifications/readed принят запрос для пометки прочтения ");
        notificationService.setNotificationsIsRead();
    }

    @Override
    public ResponseEntity<NotificationCountDto> getCount() {
        log.info("/api/v1/notifications/count принят запрос на получение количества нотификаций ");
        return ResponseEntity.ok(notificationService.getCount());
    }
}
