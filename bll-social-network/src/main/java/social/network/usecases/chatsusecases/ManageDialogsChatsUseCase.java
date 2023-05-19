package social.network.usecases.chatsusecases;

import social.network.dto.requests.CreateDialogChatRequest;
import social.network.entities.chat.chatimpl.dialog.DialogChat;

public interface ManageDialogsChatsUseCase {
    DialogChat createDialogChat(CreateDialogChatRequest request);
}
