package bikerent.service.bikerentingapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping("/")
    public String root() {
        return "redirect:/index";
    }

    /**
     * Home page.
     */
    @RequestMapping("/index")
    public String mainPage() {
        return "index";
    }
}
