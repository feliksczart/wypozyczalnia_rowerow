package bikerent.service.bikerentingapp.controllers;

import bikerent.service.bikerentingapp.domain.Login;
import bikerent.service.bikerentingapp.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    private UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String root() {
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String mainPage() {
        return "index";
    }

    @GetMapping("/registration")
    String signUp(Model model) {
        User user = new User();
        Login login = new Login();
        user.setLogin(login);
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping("/registration")
    String signUp(User user) {
        userService.signUpUser(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    String signIn() {
        return "login";
    }
}
