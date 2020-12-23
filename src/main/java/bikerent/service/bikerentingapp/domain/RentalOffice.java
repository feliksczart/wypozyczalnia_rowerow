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
public class RentalOffice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rental_office_sequence")
    @SequenceGenerator(name = "rental_office_sequence", sequenceName = "RENTAL_OFFICE_SEQ")
    private Long id;

    private String city;
    private String zip;
    private String address;

    @ManyToOne
    private Region region;

    @OneToOne
    private BlackList blackList;

    public RentalOffice(String city, String zip, String address) {
        this.city = city;
        this.zip = zip;
        this.address = address;
    }
}
