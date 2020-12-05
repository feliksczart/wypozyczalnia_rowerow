package bikerent.service.bikerentingapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BlackList implements Serializable {
    //nwm czy tak to wyglada jezeli primary key to client xd,
    // ale na pewno nie generujemy nowego id tylko jakoś wiazemy to z clientem jakoś ale nie wiem jak
    @Id
    private Long id;

    private String description;
}
