package bikerent.service.bikerentingapp.controllers;

import bikerent.service.bikerentingapp.domain.RentalOffice;
import bikerent.service.bikerentingapp.repositories.BikeRepository;
import bikerent.service.bikerentingapp.repositories.RentalOfficeRepository;
import bikerent.service.bikerentingapp.repositories.ReservationsRepository;
import bikerent.service.bikerentingapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ListBikeRestController {
    private final BikeRepository bikeRepository;
    private final RentalOfficeRepository rentalOfficeRepository;
    private final ReservationsRepository reservationsRepository;
    private final UserRepository userRepository;

    @Autowired
    public ListBikeRestController(BikeRepository bikeRepository, RentalOfficeRepository rentalOfficeRepository, ReservationsRepository reservationsRepository, UserRepository userRepository) {
        this.bikeRepository = bikeRepository;
        this.rentalOfficeRepository = rentalOfficeRepository;
        this.reservationsRepository = reservationsRepository;
        this.userRepository = userRepository;
    }


    @GetMapping(value = "/rentalOffice/{id}")
    public String bikeList(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("bikes", bikeRepository.findAll());
        return "rental-office";
    }

    @GetMapping(value = "/rentalOffice/register")
    public String rentalOfficeAdd(Model model) {
        model.addAttribute("RentalOffice", new RentalOffice());
        return "register-rental-office";
    }

    @PostMapping(value = "/rentalOffice/register")
    public String rentalOfficeClaim(@ModelAttribute RentalOffice rentalOffice) {
        rentalOfficeRepository.save(rentalOffice);
        return "redirect:/rentalOffice/" + rentalOffice.getId();
    }
/*
    @PostMapping(value = "/rentalOffice/{id}/reservations/{id}")
    public String rentalOfficeAdd(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("RentalOffice", new RentalOffice());
        return "register-rental-office";
    }

 */
}