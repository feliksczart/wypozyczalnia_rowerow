package bikerent.service.bikerentingapp.bootstrap;

import bikerent.service.bikerentingapp.Services.UserDetailsServiceImpl;
import bikerent.service.bikerentingapp.Services.UserServiceImpl;
import bikerent.service.bikerentingapp.domain.*;
import bikerent.service.bikerentingapp.repositories.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.object.SqlFunction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@AllArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final BikeRepository bikeRepository;
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final RentalOfficeRepository rentalOfficeRepository;
    private final RegionRepository regionRepository;
    private final BikeModelRepository bikeModelRepository;

    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserServiceImpl userService;
    Set<GrantedAuthority> grantedAuthority;

    @Override
    public void run(String... args) throws Exception {

//        Integer x = 20;
//        Integer y = 20;
//        Integer total = rentalRepository.count_payment(x, y);
//        System.out.println(total);
//
//        System.out.println(rentalRepository.calculate_time("2012-02-28 15:20:11", "2012-02-28 18:22:11"));

//        Region region = new Region("Wielkopolskie", "Poznań");
//        regionRepository.save(region);
//
//        RentalOffice rentalOffice = new RentalOffice("Poznan", "60-226", "Palacza 5");
//        rentalOffice.setRegion(region);
//        rentalOfficeRepository.save(rentalOffice);
//
//
//
//        User feliks = new User("Feliks", "Czart", "fff", "123", grantedAuthority);
//        userService.handleUser(feliks, userRepository, roleRepository, bCryptPasswordEncoder);
//        userService.save(feliks);
//
//        User piotr = new User("Piotr", "Derenowski", "ppp", "123", grantedAuthority);
//        userService.handleUser(piotr, userRepository, roleRepository, bCryptPasswordEncoder);
//        userService.save(piotr);
//
//        User one = new User("1", "1", "1", "1", grantedAuthority);
//        userService.handleUser(one, userRepository, roleRepository, bCryptPasswordEncoder);
//        one.setRentalOffice(rentalOffice);
//        rentalOfficeRepository.save(rentalOffice);
//        userService.save(one);
//
//
//        BikeModel bikeModel = new BikeModel("Destruktor", "Trek", "górski");
//        bikeModelRepository.save(bikeModel);
//        Bike bike = new Bike(15, "gotowy");
//        bike.setBikeModel(bikeModel);
//        bikeRepository.save(bike);
//
//        bikeModel = new BikeModel("Fire", "Norco", "miejski");
//        bikeModelRepository.save(bikeModel);
//        bike = new Bike(20, "gotowy");
//        bike.setBikeModel(bikeModel);
//        bikeRepository.save(bike);
//
//        bikeModel = new BikeModel("Water", "Schwinn", "miejski");
//        bikeModelRepository.save(bikeModel);
//        bike = new Bike(20, "w trakcie przygotowania");
//        bike.setBikeModel(bikeModel);
//        bikeRepository.save(bike);
    }
}
