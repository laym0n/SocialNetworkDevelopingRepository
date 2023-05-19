package social.network.daservices;

import social.network.entities.chat.changeshistory.chatchangeevents.ChatCreatedEvent;
import social.network.entities.chat.changeshistory.chatchangeevents.ChatDeletedEvent;
import social.network.entities.chat.changeshistory.chatchangeevents.ChatInfoChangedEvent;
import social.network.entities.chat.chatimpl.privatechat.PrivateChat;
import social.network.entities.chat.chatimpl.privatechat.PrivateChatInfo;

public interface ManagePrivateChatsDAService {
    void createPrivateChatForUserAndSaveEvent(int idUserSenderRequest, PrivateChatInfo privateChatInfo, ChatCreatedEvent event);

    void deletePrivateChatOfUserAndSaveEvent(int idPrivateChat, int idUserSenderRequest, ChatDeletedEvent event);

    void updatePrivatChatInfoOfUserAndSaveEvent(int idUserSender, int chatId, PrivateChatInfo privateChatInfo, ChatInfoChangedEvent event);
}
