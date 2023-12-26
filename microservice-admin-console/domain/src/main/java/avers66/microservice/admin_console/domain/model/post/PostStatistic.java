package avers66.microservice.admin_console.domain.model.post;

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
 * PostStatistic
 *
 * @author Georgii Vinogradov
 */

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "post_statistic")
public class PostStatistic extends BaseEntity {

    @Column(name = "date", nullable = false)
    private ZonedDateTime date;

    @Column(name = "count")
    private Integer count;

    @Column(name = "count_post_per_hour")
    @OneToMany(mappedBy = "postStatistic")
    private List<CountPostPerHour> countPostPerHour;
}
