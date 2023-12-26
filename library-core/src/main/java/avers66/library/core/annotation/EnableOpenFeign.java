package avers66.library.core.annotation;

import avers66.library.core.utils.Utils;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import avers66.library.core.config.FeignClientConfig;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * EnableOpenFeign
 *
 * @author Georgii Vinogradov
 */

@Target(ElementType.TYPE)
@Import(FeignClientConfig.class)
@Retention(RetentionPolicy.RUNTIME)
@EnableFeignClients(basePackages = Utils.BASE_PACKAGE)
public @interface EnableOpenFeign {
}
