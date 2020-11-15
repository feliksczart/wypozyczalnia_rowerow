package bikerent.service.bikerentingapp.controllers;

import bikerent.service.bikerentingapp.domain.Bike;
import bikerent.service.bikerentingapp.repositories.BikeModelRepository;
import bikerent.service.bikerentingapp.repositories.BikeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RentalOfficeBean {
    private final BikeModelRepository bikeModelRepository;
    private final BikeRepository bikeRepository;

    public void insertBikes(Bike bike, int number) {
        bikeModelRepository.save(bike.getBikeModel());
        bikeRepository.save(bike);
        for (int i = 1; i < number; i++) {
            Bike tempBike = new Bike(bike.getPricePerHour(), bike.getBikeState(), bike.getBikeModel(), bike.getRentalOffice());
            bikeRepository.save(tempBike);
        }
    }
}
