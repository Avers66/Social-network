package avers66.microservice.dialog.impl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import avers66.library.core.annotation.EnableBaseRepository;
import avers66.library.core.annotation.EnableSecurity;
import avers66.library.core.annotation.JwtProvider;

/**
 * Application
 *
 * @author Georgii Vinogradov
 */

@JwtProvider
@EnableSecurity
@EnableBaseRepository
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
