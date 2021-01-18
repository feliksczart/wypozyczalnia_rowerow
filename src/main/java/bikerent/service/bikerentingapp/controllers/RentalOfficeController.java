package bikerent.service.bikerentingapp.controllers;

import bikerent.service.bikerentingapp.beans.LoginBean;
import bikerent.service.bikerentingapp.beans.RentalOfficeBean;
import bikerent.service.bikerentingapp.domain.*;
import bikerent.service.bikerentingapp.repositories.BikeRepository;
import bikerent.service.bikerentingapp.repositories.BlackListRepository;
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
    private final BlackListRepository blackListRepository;

    @GetMapping(value = "/rentalOffice")
    public String bikeList(Model model) {
        model.addAttribute("rentalOffices", rentalOfficeRepository.findAll());
        model.addAttribute("user", loginBean.getUser());
        return "rental-office-list";
    }

    @GetMapping(value = "/rentalOffice/{id}")
    public String bikeList(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("bikes", bikeRepository.findByRentalOfficeId(id));
        model.addAttribute("id", id);
        model.addAttribute("user", loginBean.getUser());
        return "rental-office";
    }


    @GetMapping(value = "/rentalOffice/{rental_id}/remove/{bike_id}")
    public String removeBike(@PathVariable(value = "rental_id") Long rentalOfficeId, @PathVariable(value = "bike_id") Long bikeId) {
        try {
            bikeRepository.deleteBikeById(bikeId);
        } catch (Throwable e) {
            Bike bike = bikeRepository.findById(bikeId)
                    .orElseThrow(null);
            bike.setBikeState("niedostępny");
            bikeRepository.save(bike);
        }
        return "redirect:/rentalOffice/" + rentalOfficeId;
    }

    @GetMapping(value = "/rentalOffice/{rental_id}/blackList")
    public String showBlackList(@PathVariable(value = "rental_id") Long rentalOfficeId, Model model) {
        model.addAttribute("blocked", blackListRepository.findAll());
        model.addAttribute("user", loginBean.getUser());
        model.addAttribute("rentalOfficeId", rentalOfficeId);
        return "blocked-users";
    }

    @GetMapping(value = "/rentalOffice/{rental_id}/blackList/{blocked_id}")
    public String removeFromBlackList(@PathVariable(value = "rental_id") Long rentalOfficeId, @PathVariable(value = "blocked_id") Long blocked_id) {
        blackListRepository.deleteById(blocked_id);
        return "redirect:/rentalOffice/" + rentalOfficeId + "/blackList";
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
        bike.setBikeState("dostępny");
        rentalOfficeBean.insertBikes(bike, number);
        return "redirect:/rentalOffice/" + id;
    }

    @GetMapping(value = "/rentalOffice/{rental_id}/bike/{bike_id}")
    public String rentBikeForm(@PathVariable(value = "rental_id") Long rentalId, @PathVariable(value = "bike_id") Long bikeId, Model model) {
        model.addAttribute("user", loginBean.getUser());
        model.addAttribute("rentalId", rentalId);
        model.addAttribute("bikeId", bikeId);
        model.addAttribute("rental", new Rental());
        model.addAttribute("blocked", 0);
        return "add-rental-form";
    }

    @PostMapping(value = "/rentalOffice/{rental_id}/bike/{bike_id}")
    public String rentBikeProcess(Model model, @PathVariable(value = "rental_id") Long rentalId, @PathVariable(value = "bike_id") Long bikeId, @ModelAttribute("rental") Rental rental) {
        if (blackListRepository.findBlackListByPhoneNumber(rental.getPhoneNumber()) != null) {
            model.addAttribute("user", loginBean.getUser());
            model.addAttribute("rentalId", rentalId);
            model.addAttribute("bikeId", bikeId);
            model.addAttribute("rental", new Rental());
            model.addAttribute("blocked", 1);
            return "add-rental-form";
        }
        rentalOfficeBean.bikeRental(rentalId, bikeId, rental);
        return "redirect:/myRentals";
    }

    @GetMapping(value = "/rentalOffice/{rental_id}/block")
    public String clientBlock(@PathVariable(value = "rental_id") Long rentalId, Model model) {
        model.addAttribute("user", loginBean.getUser());
        model.addAttribute("rentalId", rentalId);
        model.addAttribute("blackList", new BlackList());
        return "block-client-form";
    }

    @PostMapping(value = "/rentalOffice/{rental_id}/block")
    public String clientBlockProcess(@ModelAttribute BlackList blackList) {
        blackListRepository.save(blackList);
        return "redirect:/your/rentalOffice";
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