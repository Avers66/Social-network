package avers66.microservice.dialog.impl.service;

import avers66.microservice.dialog.impl.config.KafkaConstConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import avers66.library.core.dto.streaming.MessageDto;
import avers66.library.core.dto.streaming.StreamingMessageDto;


/**
 * KafkaProducerService
 *
 * @author Georgii Vinogradov
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, StreamingMessageDto<MessageDto>> kafkaTemplate;
    private final KafkaConstConfig kafkaConstConfig;

    public void send(StreamingMessageDto<MessageDto> messageDto){
        log.info("sent message id: {} to topic {}", messageDto.getData().getId(), kafkaConstConfig.getReplyTopic());
        kafkaTemplate.send(kafkaConstConfig.getReplyTopic(), messageDto.getRecipientId().toString(), messageDto);
    }
}
