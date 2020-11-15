package bikerent.service.bikerentingapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Rentals {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "HH:mm:ss dd/MM/yyyy")
    private Date startDate;

    @DateTimeFormat(pattern = "HH:mm:ss dd/MM/yyyy")
    private Date endDate;

    private int price;

    // mozna wypozyczyc na raz kilka rowerow?
    @ManyToOne
    private Bike bike;

    @OneToOne
    private User user;

    //to tez bym odwrócił i dał po stronie wypozyczalni
    @ManyToOne
    private RentalOffice rentalOffice;
}
