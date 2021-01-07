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
        model.addAttribute("user", loginBean.getUser());
        return "rental-office-list";
    }

    @GetMapping(value = "/rentalOffice/{id}")
    public String bikeList(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("bikes", bikeRepository.findByRentalOfficeId(id));
        //model.addAttribute("bikes", bikeRepository.findAll());
        model.addAttribute("id", id);
        model.addAttribute("user", loginBean.getUser());
        return "rental-office";
    }


    @GetMapping(value = "/rentalOffice/{rental_id}/remove/{bike_id}")
    public String removeBike(@PathVariable(value = "rental_id") Long rentalOfficeId, @PathVariable(value = "bike_id") Long bikeId) {
        try {
            bikeRepository.deleteBikeById(bikeId);
        }catch(Throwable e){
            Bike bike = bikeRepository.findById(bikeId)
                    .orElseThrow(null);
            bike.setBikeState("niedostępny");
            bikeRepository.save(bike);
        }
        return "redirect:/rentalOffice/" + rentalOfficeId;
    }

    @GetMapping(value = "/rentalOffice/{id}/bikes")
    public String bikeAddForm(@PathVariable(value = "id") Long id, Model model) {
        Integer number = 0;
        model.addAttribute("rentalOffice", rentalOfficeRepository.findById(id)
                .orElseThrow(null));
        model.addAttribute("number", number);
        model.addAttribute("bike", rentalOfficeBean.bindingModelAndBike());
        model.addAttribute("user", loginBean.getUser());
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
    public String bikeList(@PathVariable(value = "rental_id") Long rentalId, @PathVariable(value = "bike_id") Long bikeId) {
        rentalOfficeBean.bikeRental(rentalId, bikeId);
        return "redirect:/myRentals";
    }

    @GetMapping(value = "/rentalOffice/register")
    public String rentalOfficeAdd(Model model) {
        model.addAttribute("exist", 0);
        model.addAttribute("RentalOffice", new RentalOffice());
        model.addAttribute("user", loginBean.getUser());
        return "register-rental-office";
    }

    @PostMapping(value = "/rentalOffice/register")
    public String rentalOfficeClaim(@ModelAttribute RentalOffice rentalOffice, Model model) {
        if (rentalOfficeRepository.findByAddress(rentalOffice.getAddress()) != null) {
            model.addAttribute("exist", 1);
            model.addAttribute("RentalOffice", new RentalOffice());
            model.addAttribute("user", loginBean.getUser());
            return "register-rental-office";
        }
        rentalOfficeRepository.save(rentalOffice);
        User user = loginBean.getUser();
        user.setRentalOffice(rentalOffice);
        userRepository.save(user);
        return "redirect:/rentalOffice/" + rentalOffice.getId();
    }

    @GetMapping(value = "/your/rentalOffice")
    public String yourRentalOffice(Model model) {
        model.addAttribute("user", loginBean.getUser());
        return "your-rental-office";
    }

    @GetMapping(value = "/your/rentalOffice/block/{id}")
    public String blockUser(@PathVariable(value = "id") Long id) {

        return "rental-office";
    }

    @GetMapping(value = "/rentalOffice/edit")
    public String editYourRentalOffice(Model model) {
        model.addAttribute("rentalOffice", new RentalOffice());
        model.addAttribute("user", loginBean.getUser());
        return "edit-your-rental-office";
    }

    @PostMapping(value = "/rentalOffice/edit")
    public String saveYourRentalOffice(@ModelAttribute RentalOffice rentalOffice) {
        rentalOfficeBean.editRentalOffice(rentalOffice);
        return "redirect:/your/rentalOffice";
    }
}