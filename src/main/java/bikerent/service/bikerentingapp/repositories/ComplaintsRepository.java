package bikerent.service.bikerentingapp.repositories;

import bikerent.service.bikerentingapp.domain.Complaints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplaintsRepository extends CrudRepository<Complaints, Long> {
}
