package bikerent.service.bikerentingapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import oracle.sql.NUMBER;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "update_price",
                procedureName = "Rental.update_price",
                parameters = {
                        @StoredProcedureParameter(name = "rental_id", type = Integer.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "total_price", type = Integer.class, mode = ParameterMode.IN)
                }),
        @NamedStoredProcedureQuery(
                name = "count_payment",
                procedureName = "Rental.count_payment",
                parameters = {
                        @StoredProcedureParameter(name = "price", type = Integer.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "time_calculated", type = Integer.class, mode = ParameterMode.IN),
                        @StoredProcedureParameter(name = "total_price", type = Integer.class, mode = ParameterMode.OUT)
                })
})

public class Rental implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rental_sequence")
    @SequenceGenerator(name = "rental_sequence", sequenceName = "RENTAL_SEQ")
    private Long id;

    private String startDate;

    private String endDate;

    private int price;

    @ManyToOne
    private Bike bike;

    @OneToOne
    private User user;

    @ManyToOne
    private RentalOffice rentalOffice;

    @OneToOne
    private Complaints complaints;
}
