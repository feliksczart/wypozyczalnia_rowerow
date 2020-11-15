package bikerent.service.bikerentingapp.controllers;

import bikerent.service.bikerentingapp.domain.Bike;
import bikerent.service.bikerentingapp.domain.BikeModel;
import bikerent.service.bikerentingapp.domain.RentalOffice;
import bikerent.service.bikerentingapp.repositories.BikeModelRepository;
import bikerent.service.bikerentingapp.repositories.BikeRepository;
import bikerent.service.bikerentingapp.repositories.RentalOfficeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class RentalOfficeController {
    private final BikeRepository bikeRepository;
    private final RentalOfficeRepository rentalOfficeRepository;
    private final BikeModelRepository bikeModelRepository;
    private final RentalOfficeBean rentalOfficeBean;

    @GetMapping(value = "/rentalOffice")
    public String bikeList(Model model) {
        model.addAttribute("rentalOffices", rentalOfficeRepository.findAll());
        return "rental-office-list";
    }

    @GetMapping(value = "/rentalOffice/{id}")
    public String bikeList(@PathVariable(value = "id") Long id, Model model) {
        model.addAttribute("bikes", bikeRepository.findAll());
        model.addAttribute("id", id);
        return "rental-office";
    }

    @GetMapping(value = "/rentalOffice/{id}/bikes")
    public String bikeAddForm(@PathVariable(value = "id") Long id, Model model) {
        Integer number = 0;
        model.addAttribute("rentalOffice", rentalOfficeRepository.findById(id).
                orElseThrow(null));
        model.addAttribute("number", number);
        BikeModel bikemodel = new BikeModel();
        bikeModelRepository.save(bikemodel);
        model.addAttribute("bike", new Bike(bikemodel));
        return "bike-add-form";
    }

    @PostMapping(value = "/rentalOffice/{idx}/bikes")
    public String bikeAddForm(@ModelAttribute Bike bike, @RequestParam(value = "number", defaultValue = "1") Integer number, @PathVariable(value = "idx") Long id) {
        bike.setRentalOffice(rentalOfficeRepository.findById(id).
                orElseThrow(null));
        rentalOfficeBean.insertBikes(bike, number);
        return "redirect:/rentalOffice/" + id;
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