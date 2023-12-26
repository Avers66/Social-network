package avers66.microservice.geo.model;

import java.util.UUID;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(City.class)
public abstract class City_ extends avers66.library.core.model.base.BaseEntity_ {

	public static volatile SingularAttribute<City, String> nameCity;
	public static volatile SingularAttribute<City, UUID> countryId;

	public static final String NAME_CITY = "nameCity";
	public static final String COUNTRY_ID = "countryId";

}

