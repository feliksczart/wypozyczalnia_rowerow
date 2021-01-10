package bikerent.service.bikerentingapp.controllers;

import bikerent.service.bikerentingapp.beans.LoginBean;
import bikerent.service.bikerentingapp.domain.Complaints;
import bikerent.service.bikerentingapp.domain.Rental;
import bikerent.service.bikerentingapp.repositories.BikeRepository;
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
    private LoginBean loginBean;
    private BikeRepository bikeRepository;

    @GetMapping(value = "/myRentals")
    public String bikeList(Model model) {
        Long userId = loginBean.getUser().getId();
        model.addAttribute("rentals", rentalRepository.findRentalsByUserId(userId));
        model.addAttribute("user", loginBean.getUser());
        return "my-rentals";
    }

    @GetMapping(value = "/myRentals/{rental_id}")
    public String rentalEnd(@PathVariable(name = "rental_id") Long id) {
        Rental rental = rentalRepository.findRentalById(id);
        rental.getBike().setBikeState("dostępny");
        bikeRepository.save(rental.getBike());
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formatDateTime = now.format(formatter);
        rental.setEndDate(formatDateTime);
        rentalRepository.save(rental);
        Float rental_hours = rentalRepository.calculate_time(rental.getStartDate(), rental.getEndDate());
        Integer rental_payment = rentalRepository.count_payment(rental.getBike().getPricePerHour(), rental_hours.intValue());
        rentalRepository.update_price(Math.toIntExact(id), rental_payment);
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
        model.addAttribute("user", loginBean.getUser());
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
        model.addAttribute("user", loginBean.getUser());
        return "rental-complaint";
    }
}
