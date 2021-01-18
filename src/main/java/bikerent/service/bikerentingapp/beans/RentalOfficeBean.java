package bikerent.service.bikerentingapp.beans;

import bikerent.service.bikerentingapp.domain.*;
import bikerent.service.bikerentingapp.repositories.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@AllArgsConstructor
public class RentalOfficeBean {
    private final BikeModelRepository bikeModelRepository;
    private final BikeRepository bikeRepository;
    private final RentalRepository rentalRepository;
    private RentalOfficeRepository rentalOfficeRepository;
    private UserRepository userRepository;
    private RegionRepository regionRepository;
    private LoginBean loginBean;

    public void insertBikes(Bike bike) {
        bikeModelRepository.save(bike.getBikeModel());
        bikeRepository.save(bike);
    }

    public Bike bindingModelAndBike() {
        BikeModel bikemodel = new BikeModel();
        bikeModelRepository.save(bikemodel);
        Bike bike = new Bike(bikemodel);
        return bike;
    }

    public RentalOffice bindingRentalAndRegion() {
        Region region = new Region("temp", "temp");
        regionRepository.save(region);
        RentalOffice rentalOffice = new RentalOffice(region);
        return rentalOffice;
    }

    public void bikeRental(Long rentalId, Long bikeId, Rental rental) {
        Bike bike = bikeRepository.findById(bikeId)
                .orElseThrow(null);
        bike.setBikeState("niedostępny");
        bikeRepository.save(bike);
        rental.setUser(loginBean.getUser());
        rental.setBike(bike);
        rental.setRentalOffice(rentalOfficeRepository.findById(rentalId)
                .orElseThrow(null));
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = now.format(formatter);
        rental.setStartDate(formatDateTime);
        rentalRepository.save(rental);

    }

    public void editRentalOffice(RentalOffice rentalOffice) {
        User user = loginBean.getUser();
        regionRepository.save(rentalOffice.getRegion());
        if (rentalOffice.getAddress() != "")
            user.getRentalOffice().setAddress(rentalOffice.getAddress());
        if (rentalOffice.getZip() != "")
            user.getRentalOffice().setZip(rentalOffice.getZip());
        if (rentalOffice.getCity() != "")
            user.getRentalOffice().setCity(rentalOffice.getCity());
        if (rentalOffice.getRegion().getVoivodeship() != "")
            user.getRentalOffice().getRegion().setVoivodeship(rentalOffice.getRegion().getVoivodeship());
        if (rentalOffice.getRegion().getDistrict() != "")
            user.getRentalOffice().getRegion().setDistrict(rentalOffice.getRegion().getDistrict());
        rentalOfficeRepository.save(user.getRentalOffice());
        userRepository.save(user);
    }
}
