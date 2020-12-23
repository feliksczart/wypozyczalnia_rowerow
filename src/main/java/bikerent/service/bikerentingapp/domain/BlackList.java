package bikerent.service.bikerentingapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BlackList implements Serializable {
    @Id
    private Long id;

    @OneToMany
    private Set<User> banned = new HashSet<>();
}
