package bikerent.service.bikerentingapp.repositories;

import bikerent.service.bikerentingapp.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByNick(String nick);
}
