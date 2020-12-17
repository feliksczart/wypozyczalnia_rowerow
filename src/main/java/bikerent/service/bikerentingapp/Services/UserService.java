package bikerent.service.bikerentingapp.Services;

import bikerent.service.bikerentingapp.domain.User;

public interface UserService {

    void save(User user);

    User findByUsername(String username);
}
