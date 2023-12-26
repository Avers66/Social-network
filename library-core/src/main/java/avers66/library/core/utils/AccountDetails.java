package avers66.library.core.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class AccountDetails {
    private UUID id;
    private String email;
    private List<String> roles;
}
