package social.network.usecases.chatsusecases.impl.manageprivatechats;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import social.network.daservices.ManagePrivateChatsDAService;
import social.network.dto.requests.CreatePrivateChatRequest;
import social.network.dto.requests.DeletePrivateChatRequest;
import social.network.dto.requests.EditPrivateChatRequest;
import social.network.entities.chat.changeshistory.chatchangeevents.ChatCreatedEvent;
import social.network.entities.chat.changeshistory.chatchangeevents.ChatDeletedEvent;
import social.network.entities.chat.changeshistory.chatchangeevents.ChatInfoChangedEvent;
import social.network.usecases.chatsusecases.ManagePrivateChatsUseCase;

@Service
@AllArgsConstructor
public class ManagePrivateChatsUseCaseImpl implements ManagePrivateChatsUseCase {
    private ManagePrivateChatsDAService daService;

    @Override
    public void createPrivateChat(CreatePrivateChatRequest request) {
        ChatCreatedEvent event = new ChatCreatedEvent();
        daService.createPrivateChatForUserAndSaveEvent(
                request.getIdUserSenderRequest(),
                request.getPrivateChatInfoDTO().getPrivateChatInfo(),
                event
        );
    }

    @Override
    public void deletePrivateChat(DeletePrivateChatRequest request) {
        ChatDeletedEvent event = new ChatDeletedEvent();
        daService.deletePrivateChatOfUserAndSaveEvent(
                request.getIdPrivateChat(),
                request.getIdUserSenderRequest(),
                event
        );
    }

    @Override
    public void editPrivateChat(EditPrivateChatRequest request) {
        ChatInfoChangedEvent event = new ChatInfoChangedEvent();
        daService.updatePrivatChatInfoOfUserAndSaveEvent(
                request.getIdUserSender(),
                request.getChatId(),
                request.getNewInfo().getPrivateChatInfo(),
                event
        );
    }
}
