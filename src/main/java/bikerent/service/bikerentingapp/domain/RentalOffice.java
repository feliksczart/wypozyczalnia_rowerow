package bikerent.service.bikerentingapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RentalOffice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;
    private String zip;
    private String address;

    @ManyToOne
    private Region region;

    public RentalOffice(String city, String zip, String address) {
        this.city = city;
        this.zip = zip;
        this.address = address;
    }
}
