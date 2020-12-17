package bikerent.service.bikerentingapp.repositories;

import bikerent.service.bikerentingapp.domain.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
