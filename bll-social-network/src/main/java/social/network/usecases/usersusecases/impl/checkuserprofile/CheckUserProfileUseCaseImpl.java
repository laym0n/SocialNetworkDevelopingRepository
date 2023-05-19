package social.network.usecases.usersusecases.impl.checkuserprofile;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import social.network.daservices.CheckUserProfileDAService;
import social.network.dto.requests.CheckUserProfileRequest;
import social.network.dto.responses.CheckUserProfileResponse;
import social.network.entities.user.UserProfile;
import social.network.usecases.usersusecases.CheckUserProfileUseCase;

//@Service
@AllArgsConstructor
public class CheckUserProfileUseCaseImpl implements CheckUserProfileUseCase {
    private CheckUserProfileDAService daService;
    @Override
    public CheckUserProfileResponse checkUserProfile(CheckUserProfileRequest request) {
        UserProfile userProfile = daService.loadUserProfileById(request.getIdUserTarget(), request.getIdOwnerRequest());
        return new CheckUserProfileResponse(userProfile);
    }
}
