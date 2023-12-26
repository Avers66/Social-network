package avers66.microservice.authorization.impl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import avers66.library.core.annotation.EnableBaseRepository;
import avers66.library.core.annotation.EnableOpenFeign;
import avers66.library.core.annotation.EnableSecurity;
/**
 * Application
 *
 * @author Mikhail Chechetkin
 */

@EnableCaching
@EnableSecurity
@EnableOpenFeign
@EnableBaseRepository
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
