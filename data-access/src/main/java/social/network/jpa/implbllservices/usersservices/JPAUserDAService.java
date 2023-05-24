package social.network.jpa.implbllservices.usersservices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import social.network.jpa.dao.UserDAO;
import social.network.daservices.UserDAService;
import social.network.jpa.entities.UserEntity;
import social.network.entities.user.User;

import java.util.Optional;

@Service
@AllArgsConstructor
public class JPAUserDAService implements UserDAService {
    private UserDAO userDAO;
    @Override
    public Optional<User> findUserByUserName(String username) {
        Optional<UserEntity> userEntity = userDAO.findByUserName(username);
        if (userEntity.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(userEntity.get().getUser());
    }
}
