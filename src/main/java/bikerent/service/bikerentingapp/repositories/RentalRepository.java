package bikerent.service.bikerentingapp.repositories;

import bikerent.service.bikerentingapp.domain.Rental;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends CrudRepository<Rental, Long> {
}
