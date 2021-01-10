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
public class BlackList implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "blacklist_sequence")
    @SequenceGenerator(name = "blacklist_sequence", sequenceName = "BLACKLIST_SEQ")
    private Long id;

    private int phoneNumber;
}
