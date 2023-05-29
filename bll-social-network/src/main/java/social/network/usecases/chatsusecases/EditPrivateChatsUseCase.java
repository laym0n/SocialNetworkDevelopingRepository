package social.network.usecases.chatsusecases;

import social.network.dto.requests.UpdateAvatarPrivateChatRequest;
import social.network.dto.requests.UpdateDescriptionPrivateChatRequest;

public interface EditPrivateChatsUseCase {
    void updateDescription(UpdateDescriptionPrivateChatRequest request);
    void updateAvatar(UpdateAvatarPrivateChatRequest request);
}
