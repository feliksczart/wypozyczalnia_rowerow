package bikerent.service.bikerentingapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bike_model", indexes = @Index(name = "name_index", columnList = "name"))
public class BikeModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bike_model_sequence")
    @SequenceGenerator(name = "bike_model_sequence", sequenceName = "BIKE_MODEL_SEQ")
    private Long id;
    private String name;
    private String brand;
    private String type;

    public BikeModel(String name, String brand, String type) {
        this.name = name;
        this.brand = brand;
        this.type = type;
    }
}
