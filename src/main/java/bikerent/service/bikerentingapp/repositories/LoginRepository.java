package bikerent.service.bikerentingapp.repositories;

import bikerent.service.bikerentingapp.domain.Login;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends CrudRepository<Login, Long> {
    Optional<Login> findByNick(String nick);
}
