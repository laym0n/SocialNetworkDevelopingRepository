package social.network.usecases.usersusecases;

import social.network.dto.requests.UpdateAvatarRequest;
import social.network.dto.requests.UpdatePersonalInfoRequest;

import javax.security.auth.login.AccountNotFoundException;

public interface EditUserProfileUseCase {
    void updatePersonalInfo(UpdatePersonalInfoRequest request);
    void updateAvatar(UpdateAvatarRequest request);
}
