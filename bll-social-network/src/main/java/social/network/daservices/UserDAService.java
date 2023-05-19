package social.network.daservices;

import social.network.entities.user.User;

import java.util.Optional;

public interface UserDAService {
    Optional<User> findUserByUserName(String username);
}
