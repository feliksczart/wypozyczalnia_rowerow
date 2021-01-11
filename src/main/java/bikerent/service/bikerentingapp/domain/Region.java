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
@Table(name = "region", indexes = @Index(name = "vivode_index", columnList = "voivodeship"))
public class Region implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "region_sequence")
    @SequenceGenerator(name = "region_sequence", sequenceName = "REGION_SEQ")
    private Long id;

    private String voivodeship;
    private String district;

    public Region(String voivodeship, String district) {
        this.voivodeship = voivodeship;
        this.district = district;
    }
}
