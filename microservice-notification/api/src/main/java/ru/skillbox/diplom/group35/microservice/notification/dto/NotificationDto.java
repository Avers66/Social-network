package ru.skillbox.diplom.group35.microservice.notification.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * NotificationDto
 *
 * @Author Tretyakov Alexandr
 */

@Data
@Schema(description = "Dto события доп.")
public class NotificationDto {

    @Schema(description = "Id")
    private UUID id;

    @Schema(description = "Id пользователя, создавшего событие")
    private UUID authorId;

    @Schema(description = "Описание события")
    private String content;

    @Schema(description = "Тип события")
    private String notificationType;

    @Schema(description = "Время события")
    private ZonedDateTime sentTime;
}
