package bikerent.service.bikerentingapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
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
