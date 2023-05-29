package social.network.daservices;

import social.network.entities.chat.chatimpl.privatechat.PrivateChatDescription;
import social.network.entities.chat.changeshistory.chatchangeevents.ChatInfoChangedEvent;

import java.util.Optional;

public interface EditPrivateChatDAService {
    void updatePrivateChatDescriptionOfUserAndSaveEvent(int idChat, PrivateChatDescription newDescription, ChatInfoChangedEvent event);

    void updatePrivateChatAvatarOfUserAndSaveEvent(int idChat, Optional<byte[]> newAvatar, ChatInfoChangedEvent event);
}
