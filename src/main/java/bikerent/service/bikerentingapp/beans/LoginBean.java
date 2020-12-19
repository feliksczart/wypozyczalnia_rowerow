package bikerent.service.bikerentingapp.beans;

import bikerent.service.bikerentingapp.Services.SecurityService;
import bikerent.service.bikerentingapp.Services.UserService;
import bikerent.service.bikerentingapp.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LoginBean {
    private final SecurityService securityService;
    private final UserService userService;

    public User getUser() {
        String username = securityService.findLoggedInUsername();
        User user = userService.findByUsername(username);
        return user;
    }
}
