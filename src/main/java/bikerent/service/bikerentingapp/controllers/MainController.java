package bikerent.service.bikerentingapp.controllers;

import bikerent.service.bikerentingapp.Services.UserService;
import bikerent.service.bikerentingapp.beans.LoginBean;
import bikerent.service.bikerentingapp.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class MainController {

    private UserService userService;
    private LoginBean loginBean;

    @RequestMapping("/")
    public String root() {
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String mainPage(Model model, Principal principal) {
//        model.addAttribute("username", loginBean.getUser(principal).getName(););
        return "index";
    }

    @GetMapping("/registration")
    String signUp(Model model) {

        return "registration";
    }

    @PostMapping("/registration")
    String signUp(User user) {
        //userService.signUpUser(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    String signIn() {
        return "login";
    }
}
