package avers66.microservice.streaming.impl.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import avers66.library.core.dto.streaming.MessageDto;
import avers66.library.core.dto.streaming.StreamingMessageDto;
import avers66.microservice.streaming.impl.kafka.KafkaConstConfig;
import avers66.microservice.streaming.resource.api.dto.AccountOnlineDto;

/**
 * KafkaProducerService
 *
 * @author Marat Safagareev
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class KafkaProducerService {

  private final KafkaTemplate<String, Object> kafkaTemplate;
  private final KafkaConstConfig kafkaConstConfig;

  public void send(AccountOnlineDto accountOnlineDto) {
    String topic = kafkaConstConfig.getReplyAccountTopic();
    log.info("Sending account status message id: {} to topic {}", accountOnlineDto.getId(), topic);
    kafkaTemplate.send(topic, accountOnlineDto.getId().toString(), accountOnlineDto);
  }

  public void send(StreamingMessageDto<MessageDto> messageDto) {
    String topic = kafkaConstConfig.getReplyDialogTopic();
    log.info("Sending dialog message id: {} to topic: {}", messageDto.getData().getId(), topic);
    kafkaTemplate.send(topic, messageDto.getRecipientId().toString(), messageDto);
  }
}
