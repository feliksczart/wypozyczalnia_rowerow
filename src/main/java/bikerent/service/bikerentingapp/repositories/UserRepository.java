package bikerent.service.bikerentingapp.repositories;

import bikerent.service.bikerentingapp.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByNick(String nick);
}
