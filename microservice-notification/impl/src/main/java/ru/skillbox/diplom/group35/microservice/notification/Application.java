package ru.skillbox.diplom.group35.microservice.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.skillbox.diplom.group35.library.core.annotation.EnableBaseRepository;
import ru.skillbox.diplom.group35.library.core.annotation.EnableOpenFeign;
import ru.skillbox.diplom.group35.library.core.annotation.EnableSecurity;
import ru.skillbox.diplom.group35.library.core.annotation.JwtProvider;

/**
 * Application
 *
 * @Author Tretyakov Alexandr
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