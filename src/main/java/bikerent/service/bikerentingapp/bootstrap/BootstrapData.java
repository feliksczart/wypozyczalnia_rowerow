package bikerent.service.bikerentingapp.bootstrap;

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

    public BootstrapData(BikeRepository bikeRepository, UserRepository userRepository) {
        this.bikeRepository = bikeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Bike bike = new Bike(15, "dobry");
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        bikeRepository.save(bike);
    }
}
