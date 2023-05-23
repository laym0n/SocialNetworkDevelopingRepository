package social.network.daservices;

import social.network.entities.user.UserProfile;

import javax.security.auth.login.AccountNotFoundException;

public interface EditUserProfileDAService {
    void updateUserProfileWithoutAvatar(UserProfile userProfile);

    void updateAvatarUser(int userId, byte[] newAvatar);
}
