package avers66.microservice.notification.mapper;

import avers66.microservice.notification.dto.NotificationSettingDto;
import avers66.microservice.notification.model.NotificationSetting;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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
