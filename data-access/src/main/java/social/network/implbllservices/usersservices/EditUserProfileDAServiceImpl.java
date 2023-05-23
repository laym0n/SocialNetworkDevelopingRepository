package social.network.implbllservices.usersservices;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import social.network.dao.UserDAO;
import social.network.daservices.EditUserProfileDAService;
import social.network.entities.UserEntity;
import social.network.entities.user.UserProfile;
import social.network.exceptions.EntityNotFoundException;

import javax.security.auth.login.AccountNotFoundException;

@Service
@AllArgsConstructor
public class EditUserProfileDAServiceImpl implements EditUserProfileDAService {
    private UserDAO userDAO;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void updateUserProfileWithoutAvatar(UserProfile userProfile) {
        int countRowsUpdated = userDAO.updateProfileFields(
                userProfile.getOwner().getFirstName(),
                userProfile.getOwner().getSecondName(),
                userProfile.getBirthday().orElse(null),
                userProfile.getOwner().getIdUser()
        );
        checkIfProfileUpdated(countRowsUpdated, userProfile.getOwner().getIdUser());
        detachMaybeLoadedUserEntity(userProfile.getOwner().getIdUser());
    }

    @Override
    public void updateAvatarUser(int userId, byte[] newAvatar) {
        int countRowsUpdated = userDAO.updateAvatar(
                newAvatar,
                userId
        );
        checkIfProfileUpdated(countRowsUpdated, userId);
        detachMaybeLoadedUserEntity(userId);
    }
    private void detachMaybeLoadedUserEntity(int id) {
        UserEntity loadedEntity = entityManager.find(UserEntity.class, id);
        if (loadedEntity != null) {
            entityManager.detach(loadedEntity);
        }
    }
    private void checkIfProfileUpdated(int countRowsUpdated, int idUser) {
        if (countRowsUpdated == 0) {
            throw new EntityNotFoundException("User with id " + idUser + " not found");
        }
    }
}
