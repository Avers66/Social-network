package ru.skillbox.diplom.group35.microservice.notification.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.skillbox.diplom.group35.library.core.dto.streaming.EventNotificationDto;
import ru.skillbox.diplom.group35.library.core.dto.streaming.StreamingMessageDto;
import ru.skillbox.diplom.group35.library.core.security.config.TechnicalUserConfig;
import ru.skillbox.diplom.group35.library.core.utils.SecurityUtil;
import ru.skillbox.diplom.group35.microservice.notification.mapper.NotificationMapper;
import ru.skillbox.diplom.group35.microservice.notification.mapper.NotificationSettingMapper;
import ru.skillbox.diplom.group35.microservice.notification.dto.*;

import ru.skillbox.diplom.group35.microservice.notification.model.Notification;
import ru.skillbox.diplom.group35.microservice.notification.model.NotificationSetting;
import ru.skillbox.diplom.group35.microservice.notification.repository.NotificationRepository;
import ru.skillbox.diplom.group35.microservice.notification.repository.NotificationSettingRepository;
import ru.skillbox.diplom.group35.microservice.friend.feignclient.FriendFeignClient;



import java.util.List;
import java.util.UUID;

/**
 * NotificationService
 *
 * @Author Tretyakov Alexandr
 */


@Slf4j
@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationSettingRepository notificationSettingRepository;
    private final NotificationRepository notificationRepository;
    private final NotificationSettingMapper notificationSettingMapper;
    private final NotificationMapper notificationMapper;
    private final SecurityUtil securityUtil;
    private final FriendFeignClient friendFeignClient;
    private final TechnicalUserConfig technicalUserConfig;
    private final KafkaTemplate kafkaTemplate;

    public NotificationSettingDto getSetting() {
        UUID myId = securityUtil.getAccountDetails().getId();
        List<NotificationSetting> notificationSettings = notificationSettingRepository.getByAccountId(myId);
        log.info("Выданы установки нотификации " + myId);
        if (!notificationSettings.isEmpty()) return notificationSettingMapper.notificationSettingToDto(notificationSettings.get(0));
        NotificationSetting notificationSetting = new NotificationSetting(myId); //ToDo удалить строки ниже после готовности CreateSetting
        notificationSettingRepository.save(notificationSetting);
        log.info("Созданы и выданы установки нотификации для " + myId);
        return notificationSettingMapper.notificationSettingToDto(notificationSetting);
    }

    public void updateSetting(NotificationUpdateDto dto) {
        UUID myId = securityUtil.getAccountDetails().getId();
        List<NotificationSetting> notificationSettings = notificationSettingRepository.getByAccountId(myId);
        if (notificationSettings.isEmpty()) return;
        NotificationSetting notificationSetting = notificationSettings.get(0);
        switch (dto.getNotificationType()) {
            case "LIKE": notificationSetting.setEnableLike(dto.isEnable()); break;
            case "POST": notificationSetting.setEnablePost(dto.isEnable()); break;
            case "POST_COMMENT": notificationSetting.setEnablePostComment(dto.isEnable()); break;
            case "COMMENT_COMMENT": notificationSetting.setEnableCommentComment(dto.isEnable()); break;
            case "MESSAGE": notificationSetting.setEnableMessage(dto.isEnable()); break;
            case "FRIEND_REQUEST": notificationSetting.setEnableFriendRequest(dto.isEnable()); break;
            case "FRIEND_BIRTHDAY": notificationSetting.setEnableFriendBirthday(dto.isEnable()); break;
            case "SEND_EMAIL_MESSAGE": notificationSetting.setEnableSendEmailMessage(dto.isEnable()); break;
            default: return;
        }
        notificationSettingRepository.save(notificationSetting);
        log.info("Настройки нотификации обновлены ");
    }

    public ResponseEntity<Boolean> createSetting(UUID accountId) {
        NotificationSetting notificationSetting = new NotificationSetting(accountId);
        log.info("id до сохранения " + notificationSetting.getId());
        notificationSettingRepository.save(notificationSetting);
        log.info("id после сохранения " + notificationSetting.getId());
        log.info("Созданы настройки нотификации " + accountId);
        return ResponseEntity.ok((notificationSetting.getId() != null) ? true : false );
    }

    public void addEvent(EventNotificationDto event) {
        sentNotification(event);
        log.info("Записано событие " + event.toString());

    }

    public Page<NotificationsDto> getNotifications(Pageable page) {
        UUID myId = securityUtil.getAccountDetails().getId();
        Page<Notification> notifications = notificationRepository.getByReceiverIdAndIsRead(myId, false,  page);
        // notifications.forEach(n -> notificationRepository.save(n.setRead(false)));
        log.info("Получены нотификации ");
        return notifications.map(n -> new NotificationsDto(notificationMapper.notificationToDto(n)));
    }

    public void setNotificationsIsRead() {
        UUID myId = securityUtil.getAccountDetails().getId();
        Page<Notification> notifications = notificationRepository.getByReceiverIdAndIsRead(myId, false,  PageRequest.of(0, 100));
        notifications.forEach(n -> notificationRepository.save(n.setRead(true)));

    }

    public NotificationCountDto getCount() {
        UUID myId = securityUtil.getAccountDetails().getId();
        log.info("Получен счетчик нотификаций ");
        return new NotificationCountDto(new Count(notificationRepository.countByReceiverIdAndIsRead(myId, false)));
    }

    public void sentNotification(EventNotificationDto eventNotificationDto) {
        List<UUID> listFriendId = technicalUserConfig.executeByTechnicalUser(() -> friendFeignClient.getFriendIdById(eventNotificationDto.getAuthorId()).getBody());
        for (UUID friendId : listFriendId) {
            List<NotificationSetting> notificationSettings = notificationSettingRepository.getByAccountId(friendId);
            if (notificationSettings.isEmpty()) continue;
            NotificationSetting notificationSetting = notificationSettings.get(0);
            boolean isSentEnable = false;
            switch (eventNotificationDto.getNotificationType()) {
                case "LIKE": isSentEnable = notificationSetting.isEnableLike(); break;
                case "POST": isSentEnable = notificationSetting.isEnablePost(); break;
                case "POST_COMMENT": isSentEnable = notificationSetting.isEnablePostComment(); break;
                case "COMMENT_COMMENT": isSentEnable = notificationSetting.isEnableCommentComment(); break;
                case "MESSAGE": isSentEnable = notificationSetting.isEnableMessage(); break;
                case "FRIEND_REQUEST": isSentEnable = notificationSetting.isEnableFriendRequest(); break;
                case "FRIEND_BIRTHDAY": isSentEnable = notificationSetting.isEnableFriendBirthday(); break;
                case "SEND_EMAIL_MESSAGE": isSentEnable = notificationSetting.isEnableSendEmailMessage(); break;
            }
            if (isSentEnable) {
                notificationRepository.save(notificationMapper.eventToNotification(eventNotificationDto).setReceiverId(friendId));
                StreamingMessageDto<EventNotificationDto> streamingMessageDto = new StreamingMessageDto<>();
                eventNotificationDto.setReceiverId(friendId);
                streamingMessageDto.setRecipientId(friendId);
                streamingMessageDto.setData(eventNotificationDto);
                streamingMessageDto.setType("NOTIFICATION");
                kafkaTemplate.send("event.streaming", "event", streamingMessageDto);
                log.info( "Нотификация " + eventNotificationDto.toString() + "направлена в кафка для сервиса streaming");

                // ToDo Kafka Send to microservice Streaming
            }
        }
    }



}
