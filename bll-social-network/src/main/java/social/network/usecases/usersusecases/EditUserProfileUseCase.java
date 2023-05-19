package social.network.usecases.usersusecases;

import social.network.dto.requests.UpdateUserProfileRequest;

public interface EditUserProfileUseCase {
    void updateUserProfile(UpdateUserProfileRequest request);
}
