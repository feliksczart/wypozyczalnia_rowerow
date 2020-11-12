package bikerent.service.bikerentingapp.bootstrap;

import bikerent.service.bikerentingapp.controllers.UserService;
import bikerent.service.bikerentingapp.domain.Bike;
import bikerent.service.bikerentingapp.domain.User;
import bikerent.service.bikerentingapp.repositories.BikeRepository;
import bikerent.service.bikerentingapp.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final BikeRepository bikeRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public BootstrapData(BikeRepository bikeRepository, UserRepository userRepository, UserService userService) {
        this.bikeRepository = bikeRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setName("Piotr");
        user.setSurname("Derenowski");
        user.setNick("");
        user.setPassword("");
        userService.signUpUser(user);
        Bike bike = new Bike(15, "dobry");
        bikeRepository.save(bike);
    }
}
