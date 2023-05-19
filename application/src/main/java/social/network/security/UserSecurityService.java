package social.network.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import social.network.configs.SecurityConfig;
import social.network.entities.user.User;
import social.network.services.UserService;

import javax.security.auth.login.AccountNotFoundException;

@Service
@AllArgsConstructor
public class UserSecurityService implements UserDetailsService {
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User foundedUser;
        try {
            foundedUser = userService.loadUserByUserName(username);
        } catch (AccountNotFoundException e) {
            throw new UsernameNotFoundException("User not found");
        }
        return new UserSecurity(foundedUser);
    }
}
