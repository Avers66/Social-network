package ru.skillbox.diplom.group35.microservice.notification.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

/**
 * NotificationsDto
 *
 * @Author Tretyakov Alexandr
 */


@Getter
@Setter
@Schema(description = "Dto события")
public class NotificationsDto {

    public NotificationsDto() {
        timeStamp = ZonedDateTime.now();
    }

    public NotificationsDto(NotificationDto data) {
        this();
        this.data = data;
    }

    @Schema(description = "Врямя отправки события")
    private ZonedDateTime timeStamp; // $date-time

    @Schema(description = "Dto события доп.")
    private NotificationDto data;

}
