package social.network.implbllservices.usersservices;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import social.network.dao.UserDAO;
import social.network.daservices.CheckUserProfileDAService;
import social.network.entities.UserEntity;
import social.network.entities.user.UserInfo;
import social.network.entities.user.UserProfile;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CheckUserProfileDAServiceImpl implements CheckUserProfileDAService {
    private UserDAO userDAO;

    @Override
    public UserProfile loadUserProfileById(int idUserTarget, int idOwnerRequest) throws AccountNotFoundException {
        Optional<UserEntity> optionalUser = userDAO.findById(idUserTarget);
        if (optionalUser.isEmpty()) {
            throw new AccountNotFoundException("User with id " + idUserTarget + " not found");
        }
        UserEntity user = optionalUser.get();
        UserProfile result = user.getUserProfile();
        return result;
    }

    @Override
    public List<UserInfo> loadFriendWithoutAvatars(int countFriends) {
        return null;
    }
}
