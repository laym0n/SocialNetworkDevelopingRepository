package social.network.usecases.usersusecases.impl.edituserprofile;

import org.springframework.stereotype.Service;
import social.network.daservices.EditUserProfileDAService;
import social.network.dto.requests.UpdateAvatarRequest;
import social.network.dto.requests.UpdateUserProfileRequest;
import lombok.AllArgsConstructor;
import social.network.usecases.usersusecases.EditUserProfileUseCase;

import javax.security.auth.login.AccountNotFoundException;

@Service
@AllArgsConstructor
public class EditUserProfileUseCaseImpl implements EditUserProfileUseCase {
    private EditUserProfileDAService daService;

    @Override
    public void updateUserProfile(UpdateUserProfileRequest request) throws AccountNotFoundException {
        daService.updateUserProfileWithoutAvatar(request.getUserProfile());
    }

    @Override
    public void updateAvatar(UpdateAvatarRequest request) throws AccountNotFoundException {
        daService.updateAvatarUser(request.getIdOwnerRequest(), request.getNewAvatar());
    }
}
