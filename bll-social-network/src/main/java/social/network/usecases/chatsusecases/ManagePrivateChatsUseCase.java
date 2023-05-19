package social.network.usecases.chatsusecases;

import social.network.dto.requests.CreatePrivateChatRequest;
import social.network.dto.requests.DeletePrivateChatRequest;
import social.network.dto.requests.EditPrivateChatRequest;

public interface ManagePrivateChatsUseCase {
    void createPrivateChat(CreatePrivateChatRequest request);

    void deletePrivateChat(DeletePrivateChatRequest request);

    void editPrivateChat(EditPrivateChatRequest request);
}
