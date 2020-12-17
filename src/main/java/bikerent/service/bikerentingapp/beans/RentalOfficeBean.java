package bikerent.service.bikerentingapp.beans;

import bikerent.service.bikerentingapp.domain.*;
import bikerent.service.bikerentingapp.repositories.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class RentalOfficeBean {
    private final BikeModelRepository bikeModelRepository;
    private final BikeRepository bikeRepository;
    private final RentalRepository rentalRepository;
    private RentalOfficeRepository rentalOfficeRepository;
    private UserRepository userRepository;
    private LoginBean loginBean;

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

    public void bikeRental(Long rentalId, Long bikeId, Principal principal) {
        Rental rental = new Rental();
        rental.setUser(loginBean.getUser(principal));
        rental.setBike(bikeRepository.findById(bikeId)
                .orElseThrow(null));
        rental.setRentalOffice(rentalOfficeRepository.findById(rentalId)
                .orElseThrow(null));
        rental.setStartDate(LocalDateTime.now());
        rentalRepository.save(rental);
    }

//    public void editRentalOffice(RentalOffice rentalOffice, Principal principal) {
//        User user = loginBean.getUser(principal);
//        if (rentalOffice.getAddress() != "")
//            user.getRentalOffice().setAddress(rentalOffice.getAddress());
//        if (rentalOffice.getZip() != "")
//            user.getRentalOffice().setZip(rentalOffice.getZip());
//        if (rentalOffice.getCity() != "")
//            user.getRentalOffice().setCity(rentalOffice.getCity());
//        rentalOfficeRepository.save(user.getRentalOffice());
//        userRepository.save(user);
//    }
}
