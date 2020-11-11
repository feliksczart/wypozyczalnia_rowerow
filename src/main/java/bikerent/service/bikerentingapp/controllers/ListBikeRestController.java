package bikerent.service.bikerentingapp.controllers;

import bikerent.service.bikerentingapp.domain.RentalOffice;
import bikerent.service.bikerentingapp.repositories.BikeRepository;
import bikerent.service.bikerentingapp.repositories.RentalOfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ListBikeRestController {
    private final BikeRepository bikeRepository;
    private final RentalOfficeRepository rentalOfficeRepository;

    @Autowired
    public ListBikeRestController(BikeRepository bikeRepository, RentalOfficeRepository rentalOfficeRepository) {
        this.bikeRepository = bikeRepository;
        this.rentalOfficeRepository = rentalOfficeRepository;
    }

    @GetMapping(value = "/")
    public String mainPage() {
        return "index";
    }

    @GetMapping(value = "/register")
    public String rentalOfficeAdd(Model model) {
        model.addAttribute("RentalOffice", new RentalOffice());
        return "register-rental-office";
    }

    @PostMapping(value = "/register")
    public String rentalOfficeClaim(@ModelAttribute RentalOffice rentalOffice) {
        rentalOfficeRepository.save(rentalOffice);
        return "index";
    }
}
