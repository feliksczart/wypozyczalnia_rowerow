package bikerent.service.bikerentingapp.beans;

import bikerent.service.bikerentingapp.Services.SecurityServiceImpl;
import bikerent.service.bikerentingapp.Services.UserService;
import bikerent.service.bikerentingapp.domain.User;
import bikerent.service.bikerentingapp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LoginBean {
    private final SecurityServiceImpl securityService;
    private final UserService userService;
    private final UserRepository userRepository;

    public User getUser() {
        String username = securityService.findLoggedInUsername();
        User user = userService.findByUsername(username);
        return user;
    }

    public void editUser(User user){
        User loggedUser = getUser();
        if (user.getName() != "")
            loggedUser.setName(user.getName());
        if (user.getSurname() != "")
            loggedUser.setSurname(user.getSurname());
        if (user.getGender() != "")
            loggedUser.setGender(user.getGender());
        if (user.getAge() != 0)
            loggedUser.setAge(user.getAge());
        userRepository.save(loggedUser);
    }
}
