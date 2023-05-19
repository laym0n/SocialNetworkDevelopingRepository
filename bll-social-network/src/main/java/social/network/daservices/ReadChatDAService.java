package social.network.daservices;

import social.network.entities.chat.chatimpl.groupchat.GroupChat;

public interface ReadChatDAService {
    GroupChat loadChatWithAllMessages(int idChat);
}
