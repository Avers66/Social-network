package avers66.microservice.authorization.impl.service;

import avers66.microservice.authorization.impl.Kafka.KafkaConstConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import avers66.microservice.authorization.api.dto.KafkaDto;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final KafkaConstConfig kafkaConstConfig;

    public void sendRegistration(KafkaDto dto) {
        String topic = kafkaConstConfig.getRequestRegistration();
        log.info("Sending registration with email: {} to topic {}", dto.getEmail(), topic);
        kafkaTemplate.send(topic, dto.getEmail(), dto);
    }
    public void sendAuthentication(KafkaDto dto) {
        String topic = kafkaConstConfig.getRequestLogin();
        log.info("Sending authentication with email: {} to topic {}", dto.getEmail(), topic);
        kafkaTemplate.send(topic, dto.getEmail(), dto);
    }


}

