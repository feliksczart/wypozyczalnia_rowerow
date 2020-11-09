package bikerent.service.bikerentingapp.repositories;

import bikerent.service.bikerentingapp.domain.BikeService;
import org.springframework.data.repository.CrudRepository;

public interface BikeServiceRepository extends CrudRepository<BikeService, Long> {
}
