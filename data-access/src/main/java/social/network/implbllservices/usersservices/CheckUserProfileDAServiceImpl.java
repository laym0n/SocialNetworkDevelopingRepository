package social.network.implbllservices.usersservices;

import jakarta.persistence.EntityNotFoundException;
import social.network.dao.UserDAO;
import social.network.daservices.CheckUserProfileDAService;
import social.network.entities.UserEntity;
import social.network.entities.user.UserInfo;
import social.network.entities.user.UserProfile;

import java.util.Optional;

public class CheckUserProfileDAServiceImpl implements CheckUserProfileDAService {
    private UserDAO userDAO;

    @Override
    public UserProfile loadUserProfileById(int idUserTarget, int idOwnerRequest) {
        Optional<UserEntity> optionalUser = userDAO.findById(idUserTarget);
        if (optionalUser.isEmpty()) {
            throw new EntityNotFoundException("User with id " + idUserTarget + " not found");
        }
        UserEntity user = optionalUser.get();
        UserProfile result = new UserProfile(
                new UserInfo(
                        idUserTarget,
                        user.getUserName(),
                        user.getFirstName(),
                        user.getSecondName(),
                        user.getAvatar()
                ),
                user.getBirthday(),
                null
        );
        return result;
    }
}
