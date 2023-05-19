package social.network.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import social.network.daservices.UserDAService;
import social.network.entities.user.User;

import javax.security.auth.login.AccountNotFoundException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private UserDAService daService;

    public User loadUserByUserName(String username) throws AccountNotFoundException {
        Optional<User> user = daService.findUserByUserName(username);
        if (user.isEmpty()){
            throw new AccountNotFoundException("User with " + username + " does not exist");
        }
        return user.get();
    }
}
