package bikerent.service.bikerentingapp.controllers;

import bikerent.service.bikerentingapp.repositories.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ListBikeRestController {
    private final BikeRepository bikeRepository;

    @Autowired
    public ListBikeRestController(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }

    @GetMapping(value = "/")
    public String bikeList(Model model) {
        model.addAttribute("bikes", bikeRepository.findAll());
        return "index";
    }
}
