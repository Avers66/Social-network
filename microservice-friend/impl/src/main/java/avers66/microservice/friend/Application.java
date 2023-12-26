package avers66.microservice.friend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import avers66.library.core.annotation.EnableBaseRepository;
import avers66.library.core.annotation.EnableOpenFeign;
import avers66.library.core.annotation.EnableSecurity;
import avers66.library.core.annotation.JwtProvider;

/**
 * Application
 *
 * @author Grigory Dyakonov
 */
@JwtProvider
@EnableSecurity
@EnableOpenFeign
@EnableBaseRepository
@SpringBootApplication
//        (exclude = { SecurityAutoConfiguration.class,
//        ManagementWebSecurityAutoConfiguration.class})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
