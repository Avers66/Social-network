package ru.skillbox.diplom.group35.microservice.notification.resource;

/**
 * NotificationController
 *
 * @Author Tretyakov Alexandr
 */

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillbox.diplom.group35.library.core.dto.streaming.EventNotificationDto;
import ru.skillbox.diplom.group35.microservice.notification.dto.*;


import java.util.UUID;


@Tag(name = "Notification service", description = "Сервис оповещений")
@ApiResponse(responseCode = "200", description = "Successful operation")
@ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
@RequestMapping("/api/v1/notifications")
public interface NotificationController {

    @GetMapping("/settings")
    @SecurityRequirement(name = "JWT")
    @Operation(description = "Получение настроек оповещений")
    ResponseEntity<NotificationSettingDto> getSetting();

    @PutMapping("/settings")
    @SecurityRequirement(name = "JWT")
    @Operation(description = "Коррекция настроек оповещений")
    void updateSetting(@RequestBody NotificationUpdateDto dto);

    @PostMapping("/settings{id}")
    @SecurityRequirement(name = "JWT")
    @Operation(description = "Создание настроек оповещений")
    ResponseEntity<Boolean> createSetting(@PathVariable(name = "id") UUID id);

    @PostMapping("/add")
    @SecurityRequirement(name = "JWT")
    @Operation(description = "Создание события")
    void addEvent(@RequestBody EventNotificationDto event);

    @GetMapping
    @SecurityRequirement(name = "JWT")
    @Operation(description = "Получение событий")
    ResponseEntity<Page<NotificationsDto>> getNotifications(Pageable page);

    @PutMapping("/readed")
    @SecurityRequirement(name = "JWT")
    @Operation(description = "Отметить все события, как прочитанные")
    void setIsRead();

    @GetMapping("/count")
    @SecurityRequirement(name = "JWT")
    @Operation(description = "Получить счетчик количества событий")
    ResponseEntity<NotificationCountDto> getCount();




}
