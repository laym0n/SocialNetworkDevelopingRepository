package social.network.usecases.usersusecases.impl.edituserprofile.handlerssendevent;

import social.network.dto.requests.UpdateUserProfileRequest;

public interface SendEventsUserUpdateProfileStrategy {
    void sendEventUserUpdateProfile(UpdateUserProfileRequest request);
}
