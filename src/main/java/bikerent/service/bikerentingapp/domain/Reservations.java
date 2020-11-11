package bikerent.service.bikerentingapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reservations {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // jedna rezerwacja na jednego klienta?
    @OneToOne
    private Client client;

    //ze wiele rowerow mozna dac do rezerwacji? nwm xD
    @OneToMany
    private Bike bike;
}
