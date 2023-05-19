package social.network.implbllservices.usersservices;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import social.network.dao.UserDAO;
import social.network.daservices.EditUserProfileDAService;
import social.network.entities.user.UserProfile;

@Service
@AllArgsConstructor
public class EditUserProfileDAServiceImpl implements EditUserProfileDAService {
    private UserDAO userDAO;

    @Override
    public void updateUserProfile(UserProfile userProfile) {
        int countRowsUpdated = userDAO.updateProfileFields(
                userProfile.getOwner().getFirstName(),
                userProfile.getOwner().getSecondName(),
                userProfile.getOwner().getAvatar(),
                userProfile.getBirthday(),
                userProfile.getOwner().getIdUser()
        );
        if (countRowsUpdated == 0) {
            throw new EntityNotFoundException("User with id " + userProfile.getOwner().getIdUser() + " not found");
        }
    }
}
