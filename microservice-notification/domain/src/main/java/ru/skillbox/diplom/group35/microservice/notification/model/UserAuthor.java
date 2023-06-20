package ru.skillbox.diplom.group35.microservice.notification.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

/**
 * UserCreator
 *
 * @Author Tretyakov Alexandr
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthor implements Serializable {
    private UUID userId;
    private String email;
}
