package ru.skillbox.diplom.group35.microservice.notification.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

/**
 * NotificationCountDto
 *
 * @Author Tretyakov Alexandr
 */

@Getter
@Setter
@Schema(description = "Dto получения счетчика событий")
public class NotificationCountDto {

    public NotificationCountDto() {
        timeStamp = ZonedDateTime.now();
    }

    public NotificationCountDto (Count data) {
        this();
        this.data = data;
    }
    @Schema(description = "Время выдачи показаний счетчика")
    private ZonedDateTime timeStamp;
    @Schema(description = "Dto получения счетчика событий доп.")
    private Count data;
}
