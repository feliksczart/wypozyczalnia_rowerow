package bikerent.service.bikerentingapp.beans;

import bikerent.service.bikerentingapp.domain.Login;
import bikerent.service.bikerentingapp.domain.User;
import bikerent.service.bikerentingapp.repositories.LoginRepository;
import bikerent.service.bikerentingapp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
@AllArgsConstructor
public class LoginBean {
    private final LoginRepository loginRepository;
    private final UserRepository userRepository;

    public User bindingUserAndLogin() {
        User user = new User();
        Login login = new Login();
        user.setLogin(login);
        return user;
    }

    public User getUser(Principal principal) {
        String nick = principal.getName();
        Login login = loginRepository.findByNick(nick)
                .orElseThrow(null);
        User user = userRepository.findById(login.getId())
                .orElseThrow(null);
        return user;
    }
}
