package avers66.microservice.geo.model;

import lombok.Getter;
import lombok.Setter;
import avers66.library.core.model.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

/**
 * City
 *
 * @author Tretyakov Alexandr
 */

@Setter
@Getter
@Entity
@Table(name = "city")
public class City extends BaseEntity { //extends BaseEntity

    @Column(nullable = false, name = "name_city")
    private String nameCity;

    @Column(nullable = false, name = "country_id")
    private UUID countryId;
}
