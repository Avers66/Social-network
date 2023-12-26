package avers66.library.core.annotation;

import avers66.library.core.exception.BaseExceptionHandler;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({BaseExceptionHandler.class})
public @interface EnableExceptionHandler {
}