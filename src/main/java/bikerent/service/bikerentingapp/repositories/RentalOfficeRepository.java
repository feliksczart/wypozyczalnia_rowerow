package bikerent.service.bikerentingapp.repositories;

import bikerent.service.bikerentingapp.domain.RentalOffice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalOfficeRepository extends CrudRepository<RentalOffice, Long> {

    void deleteRentalOfficeById(Long rentalOfficeId);
}
