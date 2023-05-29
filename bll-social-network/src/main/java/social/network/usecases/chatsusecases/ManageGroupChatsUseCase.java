package social.network.usecases.chatsusecases;

import social.network.dto.requests.chat.*;

public interface ManageGroupChatsUseCase {
    void createChat(CreateGroupChatRequest request);
}
