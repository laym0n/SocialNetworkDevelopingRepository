package social.network.usecases.chatsusecases.impl.managedialogschats;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import social.network.daservices.ManageDialogsChatsDAService;
import social.network.dto.requests.CreateDialogChatRequest;
import social.network.entities.chat.changeshistory.chatchangeevents.ChatCreatedEvent;
import social.network.entities.chat.chatimpl.dialog.DialogChat;
import social.network.usecases.chatsusecases.ManageDialogsChatsUseCase;

import java.security.InvalidParameterException;

@Service
@AllArgsConstructor
public class ManageDialogsChatsUseCaseImpl implements ManageDialogsChatsUseCase {
    private ManageDialogsChatsDAService daService;

    @Override
    public DialogChat createDialogChat(CreateDialogChatRequest request) {
        boolean isUsersInBlackListEachOthers = daService.checkIfUsersInBlackListsEachOther(
                request.getIdUserToDialog(),
                request.getIdUserSenderRequest()
        );
        if (isUsersInBlackListEachOthers) {
            throw new InvalidParameterException("Users in black list each other");
        }
        ChatCreatedEvent event = new ChatCreatedEvent();
        DialogChat newChat = daService.saveDialogChatWithMessageAndSaveEvent(
                request.getMessageDTO().getMessage(),
                request.getIdUserToDialog(),
                request.getIdUserSenderRequest(),
                event
        );
        return newChat;
    }
}
