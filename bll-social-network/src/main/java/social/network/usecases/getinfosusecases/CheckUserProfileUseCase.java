package social.network.usecases.getinfosusecases;

import social.network.dto.requests.CheckUserProfileRequest;
import social.network.dto.responses.CheckUserProfileResponse;

import javax.security.auth.login.AccountNotFoundException;

public interface CheckUserProfileUseCase {
    CheckUserProfileResponse checkUserProfile(CheckUserProfileRequest request) throws AccountNotFoundException;
}
