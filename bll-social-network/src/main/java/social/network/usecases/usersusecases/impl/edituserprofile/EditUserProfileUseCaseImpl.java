package social.network.usecases.usersusecases.impl.edituserprofile;

import org.springframework.stereotype.Service;
import social.network.daservices.EditUserProfileDAService;
import social.network.dto.requests.UpdateUserProfileRequest;
import lombok.AllArgsConstructor;
import social.network.usecases.usersusecases.EditUserProfileUseCase;
import social.network.usecases.usersusecases.impl.edituserprofile.handlerssendevent.SendEventsUserUpdateProfileStrategy;

@Service
@AllArgsConstructor
public class EditUserProfileUseCaseImpl implements EditUserProfileUseCase {
    private EditUserProfileDAService daService;
    private SendEventsUserUpdateProfileStrategy sendEventsUpdateProfileStrategy;

    @Override
    public void updateUserProfile(UpdateUserProfileRequest request) {
        daService.updateUserProfile(request.getUserProfile());
        sendEventsUpdateProfileStrategy.sendEventUserUpdateProfile(request);
    }
}
