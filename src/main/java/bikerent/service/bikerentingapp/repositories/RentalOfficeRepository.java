package bikerent.service.bikerentingapp.repositories;

import bikerent.service.bikerentingapp.domain.RentalOffice;
import org.springframework.data.repository.CrudRepository;

public interface RentalOfficeRepository extends CrudRepository<RentalOffice, Long> {
}
