package social.network.usecases.getinfosusecases.impl.checkuserprofile;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import social.network.daservices.CheckUserProfileDAService;
import social.network.dto.requests.CheckUserProfileRequest;
import social.network.dto.responses.CheckUserProfileResponse;
import social.network.entities.user.UserInfo;
import social.network.entities.user.UserProfile;
import social.network.usecases.getinfosusecases.CheckUserProfileUseCase;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;

@Service
@AllArgsConstructor
public class CheckUserProfileUseCaseImpl implements CheckUserProfileUseCase {
    private CheckUserProfileDAService daService;
    @Override
    public CheckUserProfileResponse checkUserProfile(CheckUserProfileRequest request) throws AccountNotFoundException {
        UserProfile userProfile = daService.loadUserProfileById(request.getIdUserTarget(), request.getIdOwnerRequest());
        List<UserInfo> friendInfos = daService.loadFriendWithoutAvatars(request.getCountFriends());
        return new CheckUserProfileResponse(userProfile);
    }
}
