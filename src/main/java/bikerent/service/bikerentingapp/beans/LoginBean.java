package bikerent.service.bikerentingapp.beans;

import bikerent.service.bikerentingapp.domain.Login;
import bikerent.service.bikerentingapp.domain.User;
import org.springframework.stereotype.Component;

@Component
public class LoginBean {
    public User bindingUserAndLogin() {
        User user = new User();
        Login login = new Login();
        user.setLogin(login);
        return user;
    }
}
