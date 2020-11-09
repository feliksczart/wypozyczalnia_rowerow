package bikerent.service.bikerentingapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Bike {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int pricePerHour;
    private String bikeState;

    @ManyToOne
    private BikeModel bikeModel;

    @OneToOne
    private BikeService bikeService;
}
