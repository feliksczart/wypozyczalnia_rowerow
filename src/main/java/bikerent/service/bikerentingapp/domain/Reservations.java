package bikerent.service.bikerentingapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    private User user;

    //ze wiele rowerow mozna dac do rezerwacji? nwm xD
    @OneToMany
    private Set<Bike> bikes = new HashSet<>();
}
