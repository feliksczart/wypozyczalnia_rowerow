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
@Table(name = "complaints", indexes = @Index(name = "title_index", columnList = "title"))
public class Complaints implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "complaints_sequence")
    @SequenceGenerator(name = "complaints_sequence", sequenceName = "COMPLAINTS_SEQ")
    private Long id;

    private String title;
    private String description;

    @OneToOne
    private Rental rental;
}
