package social.network.daservices;

import social.network.entities.chat.changeshistory.chatchangeevents.ChatCreatedEvent;
import social.network.entities.chat.changeshistory.chatchangeevents.ChatDeletedEvent;
import social.network.entities.chat.chatimpl.privatechat.PrivateChat;

public interface ManagePrivateChatsDAService {
    void createPrivateChatForUserAndSaveEvent(int idUser, PrivateChat newPrivateChat, ChatCreatedEvent event);

    void SaveEventForChatAndSetNotActiveChat(int idPrivateChat, ChatDeletedEvent event);
}
