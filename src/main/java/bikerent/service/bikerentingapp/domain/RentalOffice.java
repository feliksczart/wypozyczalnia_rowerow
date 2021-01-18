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
@Table(name = "RentalOffice", indexes = @Index(name = "address_index", columnList = "address"))
public class RentalOffice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rental_office_sequence")
    @SequenceGenerator(name = "rental_office_sequence", sequenceName = "RENTAL_OFFICE_SEQ")
    private Long id;

    private String city;
    private String zip;

    @Column(name = "address", nullable = false)
    private String address;

    @ManyToOne
    private Region region;

    public RentalOffice(String city, String zip, String address) {
        this.city = city;
        this.zip = zip;
        this.address = address;
    }

    public RentalOffice(Region region) {

    }
}
