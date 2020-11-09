package bikerent.service.bikerentingapp.bootstrap;

import bikerent.service.bikerentingapp.domain.Bike;
import bikerent.service.bikerentingapp.repositories.BikeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final BikeRepository bikeRepository;

    public BootstrapData(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Bike bike = new Bike(15, "dobry");
        bikeRepository.save(bike);
    }
}
