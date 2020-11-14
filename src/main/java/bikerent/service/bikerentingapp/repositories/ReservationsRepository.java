package bikerent.service.bikerentingapp.repositories;

import bikerent.service.bikerentingapp.domain.Reservations;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationsRepository extends CrudRepository<Reservations, Long> {
}
