package bikerent.service.bikerentingapp.beans;

import bikerent.service.bikerentingapp.domain.Bike;
import bikerent.service.bikerentingapp.domain.BikeModel;
import bikerent.service.bikerentingapp.domain.Rental;
import bikerent.service.bikerentingapp.repositories.BikeModelRepository;
import bikerent.service.bikerentingapp.repositories.BikeRepository;
import bikerent.service.bikerentingapp.repositories.RentalOfficeRepository;
import bikerent.service.bikerentingapp.repositories.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class RentalOfficeBean {
    private final BikeModelRepository bikeModelRepository;
    private final BikeRepository bikeRepository;
    private final RentalRepository rentalRepository;
    private RentalOfficeRepository rentalOfficeRepository;

    public void insertBikes(Bike bike, int number) {
        bikeModelRepository.save(bike.getBikeModel());
        bikeRepository.save(bike);
        for (int i = 1; i < number; i++) {
            Bike tempBike = new Bike(bike.getPricePerHour(), bike.getBikeState(), bike.getBikeModel(), bike.getRentalOffice());
            bikeRepository.save(tempBike);
        }
    }

    public Bike bindingModelAndBike() {
        BikeModel bikemodel = new BikeModel();
        bikeModelRepository.save(bikemodel);
        Bike bike = new Bike(bikemodel);
        return bike;
    }

    public void bikeRental(Long rentalId, Long bikeId) {
        Rental rental = new Rental();
        rental.setBike(bikeRepository.findById(bikeId)
                .orElseThrow(null));
        rental.setRentalOffice(rentalOfficeRepository.findById(rentalId)
                .orElseThrow(null));
        rental.setStartDate(LocalDateTime.now());
        rentalRepository.save(rental);
    }
}
