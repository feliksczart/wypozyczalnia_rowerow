package bikerent.service.bikerentingapp.repositories;

import bikerent.service.bikerentingapp.domain.BlackList;
import org.springframework.data.repository.CrudRepository;

public interface BlackListRepository extends CrudRepository<BlackList, Long> {
}
