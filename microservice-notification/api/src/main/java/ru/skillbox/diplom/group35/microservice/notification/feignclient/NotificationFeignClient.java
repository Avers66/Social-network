package ru.skillbox.diplom.group35.microservice.notification.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

/**
 * NotificationFeignClient
 *
 * @Author Tretyakov Alexandr
 */


@FeignClient(value = "NotificationFeignClient",
        url = "http://microservice-notification",
        path = "/api/v1/notifications")
public interface NotificationFeignClient {

    @PostMapping("/settings{id}")
    ResponseEntity<Boolean> createSetting(@PathVariable(name = "id") UUID id);

}
