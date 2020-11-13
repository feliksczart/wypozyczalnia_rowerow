package bikerent.service.bikerentingapp.controllers;

import bikerent.service.bikerentingapp.domain.RentalOffice;
import bikerent.service.bikerentingapp.repositories.BikeRepository;
import bikerent.service.bikerentingapp.repositories.RentalOfficeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class ListBikeController {
    private final BikeRepository bikeRepository;
    private final RentalOfficeRepository rentalOfficeRepository;

    @GetMapping(value = "/rentalOffice")
    public String bikeList(Model model) {
        model.addAttribute("rentalOffices", rentalOfficeRepository.findAll());
        return "rental-office-list";
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
}