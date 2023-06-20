package ru.skillbox.diplom.group35.microservice.notification.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skillbox.diplom.group35.microservice.notification.dto.NotificationSettingDto;
import ru.skillbox.diplom.group35.microservice.notification.model.NotificationSetting;

/**
 * SettingMapper
 *
 * @Author Tretyakov Alexandr
 */


@Mapper(componentModel = "spring")
public interface NotificationSettingMapper {

    @Mapping(target="id" , source="notificationSetting.accountId")
    NotificationSettingDto notificationSettingToDto(NotificationSetting notificationSetting);

    @Mapping(target="accountId" , source="dto.id")
    NotificationSetting dtoToNotificationSetting(NotificationSettingDto dto);
}
