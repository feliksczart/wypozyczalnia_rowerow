package bikerent.service.bikerentingapp.controllers;

import bikerent.service.bikerentingapp.beans.LoginBean;
import bikerent.service.bikerentingapp.beans.RentalOfficeBean;
import bikerent.service.bikerentingapp.domain.Bike;
import bikerent.service.bikerentingapp.domain.RentalOffice;
import bikerent.service.bikerentingapp.domain.User;
import bikerent.service.bikerentingapp.repositories.BikeRepository;
import bikerent.service.bikerentingapp.repositories.RentalOfficeRepository;
import bikerent.service.bikerentingapp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class RentalOfficeController {
    private final BikeRepository bikeRepository;
    private final RentalOfficeRepository rentalOfficeRepository;
    private final RentalOfficeBean rentalOfficeBean;
    private final LoginBean loginBean;
    private final UserRepository userRepository;

    @GetMapping(value = "/rentalOffice")
    public String bikeList(Model model) {
        model.addAttribute("rentalOffices", rentalOfficeRepository.findAll());
        return "rental-office-list";
    }

    @GetMapping(value = "/rentalOffice/{id}")
    public String bikeList(@PathVariable(value = "id") Long id, Model model) {
        //model.addAttribute("bikes", bikeRepository.findByRentalOfficeId(id));
        model.addAttribute("bikes", bikeRepository.findAll());
        model.addAttribute("id", id);
        return "rental-office";
    }

    @GetMapping(value = "/rentalOffice/{id}/bikes")
    public String bikeAddForm(@PathVariable(value = "id") Long id, Model model) {
        Integer number = 0;
        model.addAttribute("rentalOffice", rentalOfficeRepository.findById(id)
                .orElseThrow(null));
        model.addAttribute("number", number);
        model.addAttribute("bike", rentalOfficeBean.bindingModelAndBike());
        return "bike-add-form";
    }

    @PostMapping(value = "/rentalOffice/{rental_id}/bikes")
    public String bikeAddForm(@PathVariable(value = "rental_id") Long id, @ModelAttribute Bike bike, @RequestParam(value = "number", defaultValue = "1") Integer number) {
        bike.setRentalOffice(rentalOfficeRepository.findById(id).
                orElseThrow(null));
        rentalOfficeBean.insertBikes(bike, number);
        return "redirect:/rentalOffice/" + id;
    }

    @GetMapping(value = "/rentalOffice/{rental_id}/bike/{bike_id}")
    public String bikeList(@PathVariable(value = "rental_id") Long rentalId, @PathVariable(value = "bike_id") Long bikeId, Principal principal) {
        rentalOfficeBean.bikeRental(rentalId, bikeId, principal);
        return "redirect:/myRentals";
    }

    @GetMapping(value = "/rentalOffice/register")
    public String rentalOfficeAdd(Model model) {
        model.addAttribute("RentalOffice", new RentalOffice());
        return "register-rental-office";
    }

    @PostMapping(value = "/rentalOffice/register")
    public String rentalOfficeClaim(@ModelAttribute RentalOffice rentalOffice, Principal principal) {
        rentalOfficeRepository.save(rentalOffice);
        //User user = loginBean.getUser(principal);
        //user.setRentalOffice(rentalOffice);
        //userRepository.save(user);
        return "redirect:/rentalOffice/" + rentalOffice.getId();
    }

    @GetMapping(value = "/your/rentalOffice")
    public String yourRentalOffice(Model model, Principal principal) {
        //model.addAttribute("user", loginBean.getUser(principal));
        return "your-rental-office";
    }

    @GetMapping(value = "/rentalOffice/edit")
    public String editYourRentalOffice(Model model) {
        model.addAttribute("rentalOffice", new RentalOffice());
        return "edit-your-rental-office";
    }

    @PostMapping(value = "/rentalOffice/edit")
    public String saveYourRentalOffice(@ModelAttribute RentalOffice rentalOffice, Principal principal) {
        //rentalOfficeBean.editRentalOffice(rentalOffice, principal);
        return "redirect:/your/rentalOffice";
    }
}