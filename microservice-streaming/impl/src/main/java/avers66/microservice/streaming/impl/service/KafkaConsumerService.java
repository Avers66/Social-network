package avers66.microservice.streaming.impl.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.AbstractConsumerSeekAware;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import avers66.library.core.dto.streaming.EventNotificationDto;
import avers66.library.core.dto.streaming.MessageDto;
import avers66.library.core.dto.streaming.StreamingMessageDto;

/**
 * KafkaConsumerService
 *
 * @author Marat Safagareev
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class KafkaConsumerService extends AbstractConsumerSeekAware {

  private final StreamingService streamingService;

  @KafkaListener(topics = "${kafka.topic.request-dialog}")
  public void receiveMessage(
      @Payload StreamingMessageDto<MessageDto> streamingMessageDto,
      Acknowledgment acknowledgment) {
    log.info("Received message from dialog service: {}", streamingMessageDto.getData().getMessageText());
    acknowledgment.acknowledge();
    streamingService.sendToSocketMessage(streamingMessageDto);
  }

  @KafkaListener(topics = "${kafka.topic.request-notification}")
  public void receiveNotification(
      @Payload StreamingMessageDto<EventNotificationDto> streamingMessageDto,
      Acknowledgment acknowledgment) {
    log.info("Received notification: {}", streamingMessageDto.getData().getContent());
    acknowledgment.acknowledge();
    streamingService.sendToSocketMessage(streamingMessageDto);
  }

}
