package avers66.microservice.geo.model;

import lombok.Getter;
import lombok.Setter;
import avers66.library.core.model.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Country
 *
 * @author Tretyakov Alexandr
 */

@Setter
@Getter
@Entity
@Table(name = "country")
public class Country extends BaseEntity {

    @Column(nullable = false, name = "name_country")
    private String nameCountry;
}
