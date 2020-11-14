package bikerent.service.bikerentingapp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Bike {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private int pricePerHour;
    private String bikeState;

    // do edycji, bo nie wiem za bardzo jakie to relacje
    // a po drugie trzeba zaimplementować jak mają (jeśli mają) się łączyć ich tabelki joinem
    @ManyToOne
    private BikeModel bikeModel;

    @ManyToOne
    private BikeService bikeService;

    @ManyToOne
    private RentalOffice rentalOffice;

    public Bike(int pricePerHour, String bikeState) {
        this.pricePerHour = pricePerHour;
        this.bikeState = bikeState;
    }
}
