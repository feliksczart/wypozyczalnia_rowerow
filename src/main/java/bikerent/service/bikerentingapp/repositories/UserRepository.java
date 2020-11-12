package bikerent.service.bikerentingapp.repositories;

import bikerent.service.bikerentingapp.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByNick(String nick);
}
