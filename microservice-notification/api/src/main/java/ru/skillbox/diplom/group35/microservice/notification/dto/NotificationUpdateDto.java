package ru.skillbox.diplom.group35.microservice.notification.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * NotificationUpdateDto
 *
 * @Author Tretyakov Alexandr
 */


@Getter
@Setter
@Schema(description = "Dto для установки настроек оповещений")
public class NotificationUpdateDto {

    @Schema(description = "Разрешить оповещение для данного типа событий")
    private boolean enable;

    @Schema(description = "Тип события")
    private String notificationType;
}
