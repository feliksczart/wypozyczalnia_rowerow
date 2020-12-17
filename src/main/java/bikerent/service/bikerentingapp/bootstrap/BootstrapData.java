package bikerent.service.bikerentingapp.bootstrap;

import bikerent.service.bikerentingapp.Services.UserDetailsServiceImpl;
import bikerent.service.bikerentingapp.domain.*;
import bikerent.service.bikerentingapp.repositories.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final BikeRepository bikeRepository;
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final RentalOfficeRepository rentalOfficeRepository;
    private final RegionRepository regionRepository;
    private final BikeModelRepository bikeModelRepository;

    @Override
    public void run(String... args) throws Exception {
        Region region = new Region("Wielkopolskie", "Poznań");
        regionRepository.save(region);

        RentalOffice rentalOffice = new RentalOffice("Poznan", "60-226", "Palacza 5");
        rentalOffice.setRegion(region);
        rentalOfficeRepository.save(rentalOffice);

//        User user = new User();
//        user.setName("Piotr");
//        user.setSurname("Derenowski");
//        user.setRentalOffice(rentalOffice);
//        Login login = new Login("", "", false, false, Role.ADMIN);
//        loginRepository.save(login);
//        user.setLogin(login);
//        userService.signUpUser(user);

        BikeModel bikeModel = new BikeModel("Destruktor", "Ferrari", "górski");
        bikeModelRepository.save(bikeModel);

        Bike bike = new Bike(15, "dobry");
        bike.setBikeModel(bikeModel);
        bikeRepository.save(bike);

    }
}
