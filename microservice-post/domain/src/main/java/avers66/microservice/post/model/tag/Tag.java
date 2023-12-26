package avers66.microservice.post.model.tag;

import avers66.microservice.post.model.post.Post;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import avers66.library.core.model.base.BaseEntity;

/**
 * Tag
 *
 * @author Marat Safagareev
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@JsonIgnoreProperties(value = {"posts"})
public class Tag extends BaseEntity {

  @Column(name = "name", columnDefinition = "varchar(255)", nullable = false)
  private String name;

  @ManyToMany(mappedBy = "tags")
  private Set<Post> posts;
}
