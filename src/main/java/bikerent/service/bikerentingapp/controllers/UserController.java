package bikerent.service.bikerentingapp.controllers;

import bikerent.service.bikerentingapp.Services.SecurityService;
import bikerent.service.bikerentingapp.Services.UserService;
import bikerent.service.bikerentingapp.beans.LoginBean;
import bikerent.service.bikerentingapp.domain.User;
import bikerent.service.bikerentingapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class UserController {

    private final UserService userService;
    private final SecurityService securityService;
    private final UserRepository userRepository;
    private final LoginBean loginBean;

    @Autowired
    public UserController(UserService userService, SecurityService securityService, UserRepository userRepository, LoginBean loginBean) {
        this.userService = userService;
        this.securityService = securityService;
        this.userRepository = userRepository;
        this.loginBean = loginBean;
    }

    @RequestMapping("/")
    public String root() {
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public String mainPage(Model model, Principal principal) {
        model.addAttribute("user", loginBean.getUser());
        return "index";
    }

    @GetMapping("/my-profile")
    public String myProfile(Model model) {
        model.addAttribute("user", loginBean.getUser());
        return "my-profile";
    }

    @GetMapping("/my-profile/edit")
    public String editMyProfile(Model model) {
        model.addAttribute("user", loginBean.getUser());
        model.addAttribute("edituser", new User());
        return "edit-my-profile";
    }

    @PostMapping("/my-profile/edit")
    public String processMyProfile(@ModelAttribute(name = "user") User user) {
        loginBean.editUser(user);
        return "redirect:/my-profile";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("exist", 0);
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(Model model, @ModelAttribute("user") User userForm) {
        if (userRepository.findByNick(userForm.getNick()) != null) {
            model.addAttribute("exist", 1);
            model.addAttribute("user", new User());
            return "registration";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getNick(), userForm.getPassword());
        model.addAttribute("user", loginBean.getUser());

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        model.addAttribute("user", loginBean.getUser());
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @PostMapping("/logout")
    public String logout() {
        return "redirect:/index";
    }
}
