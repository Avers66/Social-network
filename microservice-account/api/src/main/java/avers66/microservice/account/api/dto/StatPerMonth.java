package avers66.microservice.account.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class StatPerMonth {
    Date date;
    Integer count;
}