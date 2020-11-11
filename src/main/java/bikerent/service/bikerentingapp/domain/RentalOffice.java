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
public class RentalOffice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String city;
    private String zip;
    private String address;

    @ManyToOne
    private Region region;

    //tu jednak bym dał żeby wypozyczalnia miała listę klientów z relacją One wypozyczalnia to many klienci xD
    @OneToMany
    private Set<Client> clients = new HashSet<>();
}
