package bikerent.service.bikerentingapp.repositories;

import bikerent.service.bikerentingapp.domain.Rental;
import net.bytebuddy.asm.Advice;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
@Transactional
public interface RentalRepository extends CrudRepository<Rental, Long> {
    void deleteRentalById(Long rentalId);

    Rental findRentalById(Long rentalId);

    List<Rental> findRentalsByUserId(Long userId);

    @Procedure
    void update_price(Integer rental_id, Integer total_price);
}
