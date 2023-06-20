package ru.skillbox.diplom.group35.microservice.notification.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.skillbox.diplom.group35.library.core.dto.streaming.EventNotificationDto;


/**
 * KafkaConsumerService
 *
 * @Author Tretyakov Alexandr
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    private final NotificationService notificationService;

    @KafkaListener(topics = "event")
    public void receiveEvent(ConsumerRecord<String, EventNotificationDto> record) {
        log.info("Принято сообщение кафка " + record.value().toString());
        EventNotificationDto eventNotificationDto = record.value();
        notificationService.addEvent(eventNotificationDto);

    }
}
