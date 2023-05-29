package social.network.usecases.chatsusecases.impl.managedialogschats;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import social.network.daservices.ManageDialogsChatsDAService;
import social.network.dto.requests.CreateDialogChatRequest;
import social.network.entities.chat.changeshistory.chatchangeevents.ChatCreatedEvent;
import social.network.exceptions.UserInBlackListException;
import social.network.usecases.chatsusecases.ManageDialogsChatsUseCase;

import java.security.InvalidParameterException;

@Service
@AllArgsConstructor
public class ManageDialogsChatsUseCaseImpl implements ManageDialogsChatsUseCase {
    private ManageDialogsChatsDAService daService;

    @Override
    public void createDialogChat(CreateDialogChatRequest request) {
        boolean isUsersInBlackListEachOthers = daService.checkIfUsersInBlackListsEachOther(
                request.getIdUserToDialog(),
                request.getIdUserSenderRequest()
        );
        if (isUsersInBlackListEachOthers) {
            throw new UserInBlackListException("Users in black list each other");
        }
        ChatCreatedEvent event = new ChatCreatedEvent();
        daService.saveDialogChatWithMessageAndSaveEvent(
                request.getMessage(),
                request.getIdUserToDialog(),
                request.getIdUserSenderRequest(),
                event
        );
    }
}
