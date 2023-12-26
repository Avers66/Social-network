package avers66.library.core.repository;

import avers66.library.core.model.base.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.lang.NonNull;

import java.util.UUID;

@NoRepositoryBean
public interface BaseRepository<Entity extends BaseEntity> extends JpaRepository<Entity, UUID>,
                                                                    JpaSpecificationExecutor<Entity> {
    void delete(@NonNull Entity entity);
    void deleteById(@NonNull UUID id);

    void deleteAll(@NonNull Iterable<? extends Entity> entities);
    void hardDelete(Entity entity);
    void hardDeleteById(UUID id);
    @NonNull
    Entity getById(@NonNull UUID id);
}
