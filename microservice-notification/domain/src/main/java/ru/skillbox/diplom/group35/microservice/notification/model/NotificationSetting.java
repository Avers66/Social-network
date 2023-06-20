package ru.skillbox.diplom.group35.microservice.notification.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.envers.Audited;
import ru.skillbox.diplom.group35.library.core.model.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

/**
 * NotificationSetting
 *
 * @Author Tretyakov Alexandr
 */
@Getter
@Setter
@Entity
@Audited
@Table(name = "notification_setting")
public class NotificationSetting extends BaseEntity {

    public NotificationSetting() {
        super.setIsDeleted(false);
        enableLike = true;
        enablePost = true;
        enablePostComment = true;
        enableCommentComment = true;
        enableMessage = true;
        enableFriendRequest = true;
        enableFriendBirthday = true;
        enableSendEmailMessage = false;
    }

    public NotificationSetting(UUID accountId){
        this();
        this.accountId = accountId;
    }



    @Column(name = "account_id")
    private UUID accountId;

    @Column(name = "enable_like")
    private boolean enableLike;

    @Column(name = "enable_post")
    private boolean enablePost;

    @Column(name = "enable_post_comment")
    private boolean enablePostComment;

    @Column(name = "enable_comment_comment")
    private boolean enableCommentComment;

    @Column(name = "enable_message")
    private boolean enableMessage;

    @Column(name = "enable_friend_request")
    private boolean enableFriendRequest;

    @Column(name = "enable_friend_birthday")
    private boolean enableFriendBirthday;

    @Column(name = "enable_send_email_message")
    private boolean enableSendEmailMessage;

}
