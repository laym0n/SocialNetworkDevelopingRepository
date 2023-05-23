package social.network.usecases.usersusecases;

import social.network.dto.requests.UpdateAvatarRequest;
import social.network.dto.requests.UpdateUserProfileRequest;

import javax.security.auth.login.AccountNotFoundException;

public interface EditUserProfileUseCase {
    void updateUserProfile(UpdateUserProfileRequest request) throws AccountNotFoundException;
    void updateAvatar(UpdateAvatarRequest request) throws AccountNotFoundException;
}
