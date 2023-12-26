package avers66.library.core.annotation;


import org.springframework.context.annotation.Import;
import avers66.library.core.security.config.SecurityConfig;
import avers66.library.core.security.config.TechnicalUserConfig;
import avers66.library.core.utils.SecurityUtil;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



@Import({SecurityConfig.class, SecurityUtil.class, TechnicalUserConfig.class})
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableSecurity {
}
