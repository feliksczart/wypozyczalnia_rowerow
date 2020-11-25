package bikerent.service.bikerentingapp.controllers;

import bikerent.service.bikerentingapp.domain.Rental;
import bikerent.service.bikerentingapp.repositories.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;

@Controller
@AllArgsConstructor
public class RentalController {
    private RentalRepository rentalRepository;

    @GetMapping(value = "/myRentals")
    public String bikeList(Model model) {
        //model.addAttribute("bikes", bikeRepository.findByRentalId(id));
        model.addAttribute("rentals", rentalRepository.findAll());
        return "my-rentals";
    }


    //trzeba poprawić te daty i liczyć czas wypożyczenia xd
    @GetMapping(value = "/myRentals/{id}")
    public String rentalEnd(@PathVariable(name = "id") Long id) {
        Rental rental = rentalRepository.findRentalById(id);
        rental.setEndDate(LocalDateTime.now());
        rental.setPrice(rental.getBike().getPricePerHour());
        rentalRepository.save(rental);
        //rentalRepository.deleteRentalById(id);
        return "redirect:/myRentals";
    }

}
