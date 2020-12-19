package bikerent.service.bikerentingapp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "client")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    @SequenceGenerator(name = "user_sequence", sequenceName = "USER_SEQ")
    private Long id;

    private String name;
    private String surname;
    private String nick;
    private String password;
    private int age;
    private String gender;
    private int phoneNumber;

    @ManyToMany
    private Set<Role> roles;

    @OneToOne
    private RentalOffice rentalOffice;

    public User(String name, String surname, String nick, String password, Set<GrantedAuthority> grantedAuthorities) {
        this.name = name;
        this.surname = surname;
        this.nick = nick;
        this.password = password;
    }

//    public User(String nick, String password, Set<GrantedAuthority> grantedAuthorities) {
//        this.nick = nick;
//        this.password = password;
//    }
}
