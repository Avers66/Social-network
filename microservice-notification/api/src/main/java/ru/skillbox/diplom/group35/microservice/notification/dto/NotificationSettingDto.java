package ru.skillbox.diplom.group35.microservice.notification.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.util.UUID;

/**
 * NotificationSettingDto
 *
 * @Author Tretyakov Alexandr
 */

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "Dto настроек оповещений")
public class NotificationSettingDto {

    @Schema(description = "Id")
    private UUID id;

    @Schema(description = "Разрешить события с типом LIKE")
    private boolean enableLike;

    @Schema(description = "Разрешить события с типом POST")
    private boolean enablePost;

    @Schema(description = "Разрешить события с типом POST_COMMENT")
    private boolean enablePostComment;

    @Schema(description = "Разрешить события с типом COMMENT_COMMENT")
    private boolean enableCommentComment;

    @Schema(description = "Разрешить события с типом MESSAGE")
    private boolean enableMessage;

    @Schema(description = "Разрешить события с типом FRIEND_REQUEST")
    private boolean enableFriendRequest;

    @Schema(description = "Разрешить события с типом FRIEND_BIRTHDAY")
    private boolean enableFriendBirthday;

    @Schema(description = "Разрешить события с типом SEND_EMAIL_MESSAGE")
    private boolean enableSendEmailMessage;
}
