package social.network.daservices;

import social.network.entities.user.PersonalInfo;

import java.util.Optional;

public interface EditUserProfileDAService {
    void updatePersonalInfo(int idUser, PersonalInfo updatedPersonalInfo);

    void updateAvatarUser(int userId, Optional<byte[]> newAvatar);
}
