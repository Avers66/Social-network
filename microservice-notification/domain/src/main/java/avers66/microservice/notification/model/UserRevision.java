package avers66.microservice.notification.model;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * UserRevision
 *
 * @Author Tretyakov Alexandr
 */

@Getter
@Setter
@Entity
@RevisionEntity(UserEnversListener.class)
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class UserRevision extends DefaultRevisionEntity {

    @Type(type = "jsonb")
    @Column(name = "user_author", columnDefinition = "jsonb")
    private UserAuthor userAuthor;


}
