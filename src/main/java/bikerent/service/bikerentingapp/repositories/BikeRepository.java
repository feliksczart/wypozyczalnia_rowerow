package bikerent.service.bikerentingapp.repositories;

import bikerent.service.bikerentingapp.domain.Bike;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BikeRepository extends CrudRepository<Bike, Long> {
    List<Bike> findByRentalOfficeId(Long id);

    Optional<Bike> findById(Long bike_id);

    void deleteBikeById(Long bike_id);
}