package bikerent.service.bikerentingapp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
