package social.network.jpa.implbllservices.usersservices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import social.network.entities.user.PersonalInfo;
import social.network.jpa.dao.UserDAO;
import social.network.daservices.EditUserProfileDAService;
import social.network.entities.user.UserProfile;
import social.network.exceptions.EntityNotFoundException;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EditUserProfileDAServiceImpl implements EditUserProfileDAService {
    private UserDAO userDAO;

    @Override
    public void updatePersonalInfo(int idUser, PersonalInfo updatedPersonalInfo) {
        int countRowsUpdated = userDAO.updateProfileFields(
                updatedPersonalInfo.getFirstName(),
                updatedPersonalInfo.getSecondName(),
                updatedPersonalInfo.getBirthday().orElse(null),
                idUser
        );
        checkIfProfileUpdated(countRowsUpdated, idUser);
    }

    @Override
    public void updateAvatarUser(int userId, Optional<byte[]> newAvatar) {
        int countRowsUpdated = userDAO.updateAvatar(
                newAvatar.orElse(null),
                userId
        );
        checkIfProfileUpdated(countRowsUpdated, userId);
    }
    private void checkIfProfileUpdated(int countRowsUpdated, int idUser) {
        if (countRowsUpdated == 0) {
            throw new EntityNotFoundException("User with id " + idUser + " not found");
        }
    }
}
