package bikerent.service.bikerentingapp.repositories;

import bikerent.service.bikerentingapp.domain.BikeModel;
import org.springframework.data.repository.CrudRepository;

public interface BikeModelRepository extends CrudRepository<BikeModel, Long> {
}
