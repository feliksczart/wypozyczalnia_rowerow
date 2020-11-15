package bikerent.service.bikerentingapp.bootstrap;

import bikerent.service.bikerentingapp.controllers.UserService;
import bikerent.service.bikerentingapp.domain.*;
import bikerent.service.bikerentingapp.repositories.BikeRepository;
import bikerent.service.bikerentingapp.repositories.LoginRepository;
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
    private final LoginRepository loginRepository;

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setName("Piotr");
        user.setSurname("Derenowski");
        Login login = new Login("", "", false, false);
        loginRepository.save(login);
        user.setLogin(login);
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
