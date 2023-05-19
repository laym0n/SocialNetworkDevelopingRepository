package social.network.usecases.usersusecases.impl.edituserprofile.handlerssendevent;

import social.network.dto.requests.UpdateUserProfileRequest;

import java.util.Optional;

public class SendEventsChainImplStrategy implements SendEventsUserUpdateProfileStrategy {
    private SendEventsUserUpdateProfileStrategy currentHandler;
    private Optional<SendEventsUserUpdateProfileStrategy> nextHandler;

    @Override
    public void sendEventUserUpdateProfile(UpdateUserProfileRequest request) {
        currentHandler.sendEventUserUpdateProfile(request);
        if (nextHandler.isPresent()) {
            nextHandler.get().sendEventUserUpdateProfile(request);
        }
    }
}
