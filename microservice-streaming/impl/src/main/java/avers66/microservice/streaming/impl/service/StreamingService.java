package avers66.microservice.streaming.impl.service;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import avers66.library.core.dto.streaming.MessageDto;
import avers66.library.core.dto.streaming.StreamingMessageDto;
import avers66.microservice.streaming.impl.utils.WebSocketPool;
import avers66.microservice.streaming.resource.api.dto.AccountOnlineDto;

/**
 * StreamingService
 *
 * @author Marat Safagareev
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class StreamingService {

  public static final String ACCOUNT_ID_FIELD = "accountId";
  private static final String TYPE_FIELD = "type";
  private static final String MESSAGE_TYPE = "MESSAGE";
  private static final String MESSAGE_STATUS_SENT = "SENT";

  private final ObjectMapper objectMapper;
  private final WebSocketPool webSocketPool;
  private final KafkaProducerService kafkaProducerService;
  private final JavaType type;

  public void establishConnection(WebSocketSession session) {
    UUID accountId = getAccountId(session);
    log.info("Connection established with accountId: {}", accountId);
    changeAccountOnline(new AccountOnlineDto(accountId, null, true));
    webSocketPool.addSession(accountId, session);
  }

  public void closeConnection(WebSocketSession session) {
    UUID accountId = getAccountId(session);
    log.info("Connection closed with accountId: {}", accountId);
    changeAccountOnline(new AccountOnlineDto(accountId, ZonedDateTime.now(), false));
    webSocketPool.removeSession(accountId);
  }

  public void handleMessage(WebSocketSession session, TextMessage message) throws IOException {
    UUID authorId = getAccountId(session);
    String payload = message.getPayload();
    log.info("Received message from user: {}", payload);
    JsonNode jsonNode = objectMapper.readTree(payload);
    if (jsonNode.get(TYPE_FIELD).textValue().equals(MESSAGE_TYPE)) {
      StreamingMessageDto<MessageDto> streamingMessageDto = objectMapper.readValue(payload, type);
      streamingMessageDto.getData().setReadStatus(MESSAGE_STATUS_SENT);
      streamingMessageDto.getData().setTime(ZonedDateTime.now());
      transmitMessage(reconstructStreamingMessage(authorId, streamingMessageDto));
    }
  }

  private StreamingMessageDto<MessageDto> reconstructStreamingMessage(UUID authorId,
      StreamingMessageDto<MessageDto> dto) {
    if (dto.getData().getConversationPartner2().equals(authorId)) {
      dto.getData().setConversationPartner2(dto.getData().getConversationPartner1());
    }
    dto.getData().setConversationPartner1(authorId);
    if (dto.getRecipientId().equals(authorId)) {
      dto.setRecipientId(dto.getData().getConversationPartner2());
    }
    return dto;
  }

  public void sendToSocketMessage(@NotNull StreamingMessageDto<?> streamingMessageDto) {
    UUID accountId = streamingMessageDto.getRecipientId();
    log.info("Check whether user: {} is connected to send the message: {}",
        accountId, streamingMessageDto);
    if (webSocketPool.poolContains(accountId)) {
      try {
        log.info("Sending message: {} to user with id: {}", streamingMessageDto, accountId);
        webSocketPool.getSession(accountId).sendMessage(
            new TextMessage(objectMapper.writeValueAsString(streamingMessageDto)));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private void changeAccountOnline(AccountOnlineDto accountOnlineDto) {
    log.info("Change user with id: {} status to: {}", accountOnlineDto.getId(),
        accountOnlineDto.getIsOnline());
    kafkaProducerService.send(accountOnlineDto);
  }

  private void transmitMessage(StreamingMessageDto<MessageDto> streamingMessageDto) {
    log.info("Transmitting message: {}", streamingMessageDto);
    kafkaProducerService.send(streamingMessageDto);
  }

  private UUID getAccountId(WebSocketSession session) {
    return (UUID) session.getAttributes().get(ACCOUNT_ID_FIELD);
  }
}
