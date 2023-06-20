package ru.skillbox.diplom.group35.microservice.notification.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Count
 *
 * @Author Tretyakov Alexandr
 */

@Getter
@Setter
@Schema(description = "Dto получения счетчика событий доп.")
public class Count {
    public Count(Long count) {
        this.count = count;
    }

    @Schema(description = "Значение счетчика событий")
    private Long count;
}
