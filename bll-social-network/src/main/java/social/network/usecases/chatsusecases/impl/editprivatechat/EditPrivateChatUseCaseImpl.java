package social.network.usecases.chatsusecases.impl.editprivatechat;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import social.network.daservices.CheckerOwnerPrivateChatDAService;
import social.network.daservices.EditPrivateChatDAService;
import social.network.dto.requests.UpdateAvatarPrivateChatRequest;
import social.network.dto.requests.UpdateDescriptionPrivateChatRequest;
import social.network.entities.chat.changeshistory.chatchangeevents.ChatInfoChangedEvent;
import social.network.services.CheckerOwnerPrivateChat;
import social.network.usecases.chatsusecases.EditPrivateChatsUseCase;

import java.security.InvalidParameterException;

@Service
@AllArgsConstructor
public class EditPrivateChatUseCaseImpl implements EditPrivateChatsUseCase {
    private EditPrivateChatDAService daService;
    private CheckerOwnerPrivateChat checkerOwnerPrivateChat;
    @Override
    public void updateDescription(UpdateDescriptionPrivateChatRequest request) {
        checkerOwnerPrivateChat.checkIfUserIsOwnerOfPrivateChat(request.getIdUserSenderRequest(), request.getChatId());
        ChatInfoChangedEvent event = new ChatInfoChangedEvent();
        daService.updatePrivateChatDescriptionOfUserAndSaveEvent(
                request.getChatId(),
                request.getNewDescription(),
                event
        );
    }

    @Override
    public void updateAvatar(UpdateAvatarPrivateChatRequest request) {
        checkerOwnerPrivateChat.checkIfUserIsOwnerOfPrivateChat(request.getIdUserSenderRequest(), request.getChatId());
        ChatInfoChangedEvent event = new ChatInfoChangedEvent();
        daService.updatePrivateChatAvatarOfUserAndSaveEvent(
                request.getChatId(),
                request.getNewAvatar(),
                event
        );
    }
}
