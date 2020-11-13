package bikerent.service.bikerentingapp.repositories;

import bikerent.service.bikerentingapp.domain.Region;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends CrudRepository<Region, Long> {
}
