package bikerent.service.bikerentingapp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

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

    @OneToOne
    private Login login;

    @OneToOne
    private RentalOffice rentalOffice;

    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
}
