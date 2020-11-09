package bikerent.service.bikerentingapp.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BikeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String modelName;
    // ...
}
