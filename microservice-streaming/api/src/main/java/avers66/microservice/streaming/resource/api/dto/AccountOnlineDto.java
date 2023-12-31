package avers66.microservice.streaming.resource.api.dto;

import java.time.ZonedDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import avers66.library.core.dto.base.BaseDto;

/**
 * AccountOnlineDto
 *
 * @author Marat Safagareev
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AccountOnlineDto extends BaseDto {

  private UUID id;
  private ZonedDateTime lastOnlineTime;
  private Boolean isOnline;
}
