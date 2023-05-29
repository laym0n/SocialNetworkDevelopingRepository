package social.network.usecases.chatsusecases;

import social.network.dto.requests.CreateDialogChatRequest;

public interface ManageDialogsChatsUseCase {
    void createDialogChat(CreateDialogChatRequest request);
}
