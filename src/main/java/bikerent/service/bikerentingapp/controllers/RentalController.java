package bikerent.service.bikerentingapp.controllers;

import bikerent.service.bikerentingapp.repositories.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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


}
