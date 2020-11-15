package bikerent.service.bikerentingapp.bootstrap;

import bikerent.service.bikerentingapp.controllers.UserService;
import bikerent.service.bikerentingapp.domain.Bike;
import bikerent.service.bikerentingapp.domain.Region;
import bikerent.service.bikerentingapp.domain.RentalOffice;
import bikerent.service.bikerentingapp.domain.User;
import bikerent.service.bikerentingapp.repositories.BikeRepository;
import bikerent.service.bikerentingapp.repositories.RegionRepository;
import bikerent.service.bikerentingapp.repositories.RentalOfficeRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final BikeRepository bikeRepository;
    private final UserService userService;
    private final RentalOfficeRepository rentalOfficeRepository;
    private final RegionRepository regionRepository;

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setName("Piotr");
        user.setSurname("Derenowski");
        user.getLogin().setNick("");
        user.getLogin().setPassword("");
        userService.signUpUser(user);

        Bike bike = new Bike(15, "dobry");
        bikeRepository.save(bike);

        Region region = new Region("Wielkopolskie", "Poznań");
        regionRepository.save(region);

        RentalOffice rentalOffice = new RentalOffice("Poznan", "60-226", "Palacza 5");
        rentalOffice.setRegion(region);
        rentalOfficeRepository.save(rentalOffice);
    }
}
