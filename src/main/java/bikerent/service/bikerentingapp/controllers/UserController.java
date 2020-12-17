package bikerent.service.bikerentingapp.controllers;

import bikerent.service.bikerentingapp.Services.SecurityService;
import bikerent.service.bikerentingapp.Services.UserService;
import bikerent.service.bikerentingapp.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class UserController {

    private UserService userService;
    private SecurityService securityService;

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
    public String registration(Model model) {
        model.addAttribute("user", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") User userForm) {

        userService.save(userForm);

        securityService.autoLogin(userForm.getNick(), userForm.getPassword());

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }
}
