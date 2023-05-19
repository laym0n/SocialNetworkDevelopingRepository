package social.network.usecases.usersusecases.impl.edituserprofile.handlerssendevent;

import lombok.AllArgsConstructor;
import social.network.daservices.SendEventToChatsDAService;
import social.network.dto.requests.UpdateUserProfileRequest;
import social.network.entities.chat.changeshistory.chatchangeevents.ChatMemberChangedInfoEvent;

import java.util.Optional;

@AllArgsConstructor
public class SendEventsToChatsImplStrategy implements SendEventsUserUpdateProfileStrategy {
    private SendEventToChatsDAService daService;

    @Override
    public void sendEventUserUpdateProfile(UpdateUserProfileRequest request) {
        ChatMemberChangedInfoEvent event = new ChatMemberChangedInfoEvent(request.getIdOwnerRequest());
        daService.addEventToAllChatsWithUserByUserId(event, request.getIdOwnerRequest());
    }
}
