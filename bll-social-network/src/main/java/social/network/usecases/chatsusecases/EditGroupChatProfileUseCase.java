package social.network.usecases.chatsusecases;

import social.network.dto.requests.UpdateDescriptionGroupChatRequest;
import social.network.dto.requests.chat.UpdateAvatarGroupChatRequest;

public interface EditGroupChatProfileUseCase {
    void updateAvatar(UpdateAvatarGroupChatRequest request);
    void updateDescription(UpdateDescriptionGroupChatRequest request);
}
