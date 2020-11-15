package bikerent.service.bikerentingapp.controllers;

import bikerent.service.bikerentingapp.domain.User;
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
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String nick) throws UsernameNotFoundException {

        final Optional<User> optionalUser = userRepository.findByNick(nick);

        if (optionalUser.isPresent()) {
            return (UserDetails) optionalUser.get();
        } else {
            throw new UsernameNotFoundException(MessageFormat.format("User with nick {0} cannot be found.", nick));
        }
    }

    public void signUpUser(User user) {
        final String encryptedPassword = bCryptPasswordEncoder.encode(user.getLogin().getPassword());
        user.getLogin().setPassword(encryptedPassword);
        user.getLogin().setEnabled(true);
        userRepository.save(user);
    }
}