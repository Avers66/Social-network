package avers66.microservice.geo.repository;

import avers66.microservice.geo.model.City;
import org.springframework.stereotype.Repository;
import avers66.library.core.repository.BaseRepository;

import java.util.List;
import java.util.UUID;

/**
 * CityRepository
 *
 * @author Tretyakov Alexandr
 */
@Repository
public interface CityRepository extends BaseRepository<City> {

    List<City> findByCountryId(UUID countryId);

    void deleteByCountryId(UUID countryId);
}
