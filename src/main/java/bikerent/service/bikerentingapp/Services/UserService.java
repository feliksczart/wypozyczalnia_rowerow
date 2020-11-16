package bikerent.service.bikerentingapp.Services;

import bikerent.service.bikerentingapp.domain.Login;
import bikerent.service.bikerentingapp.domain.Role;
import bikerent.service.bikerentingapp.domain.User;
import bikerent.service.bikerentingapp.repositories.LoginRepository;
import bikerent.service.bikerentingapp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final LoginRepository loginRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String nick) throws UsernameNotFoundException {

        final Optional<Login> optionalUser = loginRepository.findByNick(nick);

        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new UsernameNotFoundException(MessageFormat.format("User with nick {0} cannot be found.", nick));
        }
    }

    public void signUpUser(User user) {
        Login login = user.getLogin();
        final String encryptedPassword = bCryptPasswordEncoder.encode(login.getPassword());
        login.setPassword(encryptedPassword);
        login.setEnabled(true);
        login.setRole(Role.ADMIN);
        loginRepository.save(login);
        user.setLogin(login);
        userRepository.save(user);
    }
}