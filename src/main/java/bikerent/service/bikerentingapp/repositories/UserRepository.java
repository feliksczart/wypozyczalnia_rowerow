package bikerent.service.bikerentingapp.repositories;

import bikerent.service.bikerentingapp.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
