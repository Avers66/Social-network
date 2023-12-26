package avers66.microservice.admin_console.domain.model.comment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import avers66.library.core.model.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.ZonedDateTime;
import java.util.List;

/**
 * CommentStatistic
 *
 * @author Georgii Vinogradov
 */

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "comment_statistic")
public class CommentStatistic extends BaseEntity {

    @Column(name = "date", nullable = false)
    private ZonedDateTime date;

    @Column(name = "count")
    private Integer count;

    @Column(name = "count_comment_per_hour")
    @OneToMany(mappedBy = "commentStatistic")
    private List<CountCommentPerHour> countCommentPerHour;
}
