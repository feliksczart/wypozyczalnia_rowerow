package bikerent.service.bikerentingapp.repositories;

import bikerent.service.bikerentingapp.domain.Bike;
import org.springframework.data.repository.CrudRepository;

public interface BikeRepository extends CrudRepository<Bike, Long> {
}