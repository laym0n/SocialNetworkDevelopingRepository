package social.network.usecases.usersusecases;

import social.network.dto.requests.CheckUserProfileRequest;
import social.network.dto.responses.CheckUserProfileResponse;

public interface CheckUserProfileUseCase {
    CheckUserProfileResponse checkUserProfile(CheckUserProfileRequest request);
}
