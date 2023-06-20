package ru.skillbox.diplom.group35.microservice.notification.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.skillbox.diplom.group35.library.core.model.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * Notification
 *
 * @Author Tretyakov Alexandr
 */
@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "notification")
public class Notification extends BaseEntity {

    public Notification() {
        super.setIsDeleted(false);
        isRead = false;
        sentTime = ZonedDateTime.now();
    }

    public Notification(UUID authorId, UUID receiverId, String content, String notificationType) {
        this();
        this.authorId = authorId;
        this.receiverId = receiverId;
        this.content = content;
        this.notificationType = notificationType;
    }
    @Column(name = "author_id")
    private UUID authorId;

    @Column(name = "receiver_id")
    private UUID receiverId;

    @Column(name = "content")
    private String content;

    @Column(name = "notification_type")
    private String notificationType;

    @Column(name = "sent_time")
    private ZonedDateTime sentTime;

    @Column(name = "is_read")
    private boolean isRead;


}
