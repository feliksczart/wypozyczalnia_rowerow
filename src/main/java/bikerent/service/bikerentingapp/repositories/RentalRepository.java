package bikerent.service.bikerentingapp.repositories;

import bikerent.service.bikerentingapp.domain.Rental;
import net.bytebuddy.asm.Advice;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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

    @Modifying
    @Procedure(name = "Rental.update_price")
    void update_price(@Param("rental_id") Integer rental_id, @Param("total_price") Integer total_price);

    @Query(nativeQuery = true, value = "SELECT count_payment(:price, :time_calculated) FROM dual")
    Integer count_payment(@Param("price") Integer price, @Param("time_calculated") Integer time_calculated);

    @Query(nativeQuery = true, value = "SELECT calculate_time(:start_time, :end_time) FROM dual")
    Float calculate_time(@Param("start_time") String start_time, @Param("end_time") String end_time);
}
