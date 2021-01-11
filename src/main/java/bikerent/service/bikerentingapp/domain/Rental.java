package bikerent.service.bikerentingapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private String fullName;

    private int phoneNumber;

    @ManyToOne
    private Bike bike;

    @OneToOne
    private User user;

    @ManyToOne
    private RentalOffice rentalOffice;

    @OneToOne
    private Complaints complaints;
}
