package avers66.library.core.annotation;

/**
 * EnableBaseRepository
 *
 * @author Denis_Kholmogorov
 */

import avers66.library.core.repository.BaseRepositoryImpl;
import avers66.library.core.utils.Utils;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@EnableJpaRepositories(
        repositoryBaseClass = BaseRepositoryImpl.class,
        basePackages = Utils.BASE_PACKAGE
)
@EntityScan(basePackages = Utils.BASE_PACKAGE)
public @interface EnableBaseRepository {
}
