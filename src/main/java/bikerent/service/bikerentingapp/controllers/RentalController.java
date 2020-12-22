package bikerent.service.bikerentingapp.controllers;

import bikerent.service.bikerentingapp.domain.Complaints;
import bikerent.service.bikerentingapp.domain.Rental;
import bikerent.service.bikerentingapp.repositories.ComplaintsRepository;
import bikerent.service.bikerentingapp.repositories.RentalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@AllArgsConstructor
public class RentalController {
    private RentalRepository rentalRepository;
    private ComplaintsRepository complaintsRepository;

    @GetMapping(value = "/myRentals")
    public String bikeList(Model model) {
        //model.addAttribute("bikes", bikeRepository.findByRentalId(id));
        model.addAttribute("rentals", rentalRepository.findAll());
        return "my-rentals";
    }

    //trzeba poprawić te daty i liczyć czas wypożyczenia xd
    @GetMapping(value = "/myRentals/{rental_id}")
    public String rentalEnd(@PathVariable(name = "rental_id") Long id) {
        Rental rental = rentalRepository.findRentalById(id);
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = now.format(formatter);
        rental.setEndDate(formatDateTime);
//        rental.setPrice(rental.getBike().getPricePerHour());
        rentalRepository.update_price(id, 50);
        rentalRepository.save(rental);
        //rentalRepository.deleteRentalById(id);
        return "redirect:/myRentals";
    }

    @GetMapping(value = "/myRentals/{rental_id}/complaint")
    public String rentalComplaint(@PathVariable(name = "rental_id") Long id, Model model) {
        Rental rental = rentalRepository.findRentalById(id);
        Complaints complaint = new Complaints();
        complaint.setRental(rental);
        complaintsRepository.save(complaint);
        rental.setComplaints(complaint);
        rentalRepository.save(rental);
        model.addAttribute("rental", rental);
        return "complaint-form";
    }

    @PostMapping(value = "/myRentals/{rental_id}/complaint")
    public String processComplaint(@PathVariable(name = "rental_id") Long id, @ModelAttribute("rental") Rental rental) {
        Complaints complaint = rental.getComplaints();
        complaintsRepository.save(complaint);
        Rental rental_original = rentalRepository.findRentalById(id);
        rental_original.setComplaints(complaint);
        rentalRepository.save(rental_original);
        return "redirect:/myRentals";
    }

    @GetMapping(value = "/myRentals/{rental_id}/show")
    public String rentalComplaintDisplay(@PathVariable(name = "rental_id") Long id, Model model) {
        Rental rental = rentalRepository.findRentalById(id);
        model.addAttribute("rental", rental);
        return "rental-complaint";
    }
}
