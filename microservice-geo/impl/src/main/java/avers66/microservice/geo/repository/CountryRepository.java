package avers66.microservice.geo.repository;

import avers66.microservice.geo.model.Country;
import org.springframework.stereotype.Repository;
import avers66.library.core.repository.BaseRepository;

import java.util.List;

/**
 * CountryRepository
 *
 * @author Tretyakov Alexandr
 */

@Repository
public interface CountryRepository extends BaseRepository<Country> {
    List<Country> findCountriesByNameCountry(String nameCountry);

}
