package avers66.microservice.dialog.api.dto.message;

import lombok.Data;
import lombok.experimental.Accessors;
import avers66.library.core.dto.base.BaseSearchDto;

import java.util.UUID;

@Data
@Accessors(chain = true)
public class MessageSearchDto extends BaseSearchDto {
    private UUID conversationPartner1;
    private UUID conversationPartner2;
    private String readStatus;
}
