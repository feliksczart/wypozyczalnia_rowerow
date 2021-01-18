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
@Table(name = "region", indexes = {@Index(name = "voivodeship_index", columnList = "voivodeship"),
        @Index(name = "district_index", columnList = "district")})
public class Region implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "region_sequence")
    @SequenceGenerator(name = "region_sequence", sequenceName = "REGION_SEQ")
    private Long id;

    @Column(name = "voivodeship", nullable = false)
    private String voivodeship;

    @Column(name = "district", nullable = false)
    private String district;

    public Region(String voivodeship, String district) {
        this.voivodeship = voivodeship;
        this.district = district;
    }
}
