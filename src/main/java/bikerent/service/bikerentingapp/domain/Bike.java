package bikerent.service.bikerentingapp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Bike")
public class Bike implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bike_sequence")
    @SequenceGenerator(name = "bike_sequence", sequenceName = "BIKE_SEQ")
    private Long id;

    private int pricePerHour;
    private String bikeState;

    @ManyToOne
    private BikeModel bikeModel;

    @ManyToOne
    private RentalOffice rentalOffice;

    public Bike(int pricePerHour, String bikeState) {
        this.pricePerHour = pricePerHour;
        this.bikeState = bikeState;
    }

    public Bike(BikeModel bikeModel) {
        this.bikeModel = bikeModel;
    }

    public Bike(int pricePerHour, String bikeState, BikeModel bikeModel, RentalOffice rentalOffice) {
        this.pricePerHour = pricePerHour;
        this.bikeState = bikeState;
        this.bikeModel = bikeModel;
        this.rentalOffice = rentalOffice;
    }
}
