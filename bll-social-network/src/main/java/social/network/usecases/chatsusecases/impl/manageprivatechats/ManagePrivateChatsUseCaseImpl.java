package social.network.usecases.chatsusecases.impl.manageprivatechats;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import social.network.daservices.ManagePrivateChatsDAService;
import social.network.dto.requests.CreatePrivateChatRequest;
import social.network.dto.requests.DeletePrivateChatRequest;
import social.network.entities.chat.changeshistory.chatchangeevents.ChatCreatedEvent;
import social.network.entities.chat.changeshistory.chatchangeevents.ChatDeletedEvent;
import social.network.entities.chat.chatimpl.privatechat.PrivateChat;
import social.network.services.CheckerOwnerPrivateChat;
import social.network.usecases.chatsusecases.ManagePrivateChatsUseCase;
import social.network.usecases.chatsusecases.impl.managedialogschats.ManageDialogsChatsUseCaseImpl;

import java.security.InvalidParameterException;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
public class ManagePrivateChatsUseCaseImpl implements ManagePrivateChatsUseCase {
    private static Logger log = Logger.getLogger(ManagePrivateChatsUseCaseImpl.class.getName());
    private ManagePrivateChatsDAService daService;
    private CheckerOwnerPrivateChat checkerOwnerPrivateChat;
    @Override
    public void createPrivateChat(CreatePrivateChatRequest request) {
        log.info("Get CreatePrivateChatRequest " + request);
        ChatCreatedEvent event = new ChatCreatedEvent();
        PrivateChat newPrivateChat = new PrivateChat(null, request.getNewPrivateChatProfile());
        daService.createPrivateChatForUserAndSaveEvent(
                request.getIdUserSenderRequest(),
                newPrivateChat,
                event
        );
    }

    @Override
    public void deletePrivateChat(DeletePrivateChatRequest request) {
        log.info("Get DeletePrivateChatRequest " + request);
        checkerOwnerPrivateChat.checkIfUserIsOwnerOfPrivateChat(
                request.getIdUserSenderRequest(),
                request.getIdPrivateChat()
        );
        ChatDeletedEvent event = new ChatDeletedEvent();
        daService.SaveEventForChatAndSetNotActiveChat(
                request.getIdPrivateChat(),
                event
        );
    }
}
