package avers66.microservice.geo.mapper;

import avers66.microservice.geo.dto.CityDto;
import avers66.microservice.geo.model.City;
import avers66.microservice.geo.model.Country;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import avers66.microservice.geo.dto.CountryDto;

/**
 * GeoMapper
 *
 * @Author Tretyakov Alexandr
 */

@Mapper(componentModel = "spring")
public interface GeoMapper {

    @Mapping(target = "title", source = "nameCountry")
    CountryDto countryToCountryDto(Country country);

    @Mapping(target = "title", source = "nameCity")
    CityDto cityToCityDto(City city);
}


