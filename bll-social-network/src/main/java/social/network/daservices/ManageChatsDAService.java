package social.network.daservices;

import social.network.entities.chat.chatmemberinfo.GroupChatMemberProfile;
import social.network.entities.chat.changeshistory.chatchangeevents.ChatCreatedEvent;
import social.network.entities.chat.changeshistory.chatchangeevents.ChatInfoChangedEvent;
import social.network.entities.chat.changeshistory.chatchangeevents.ChatMemberChangedInfoEvent;
import social.network.entities.chat.chatimpl.groupchat.ChatMemberRole;
import social.network.entities.chat.chatimpl.groupchat.GroupChat;

import java.util.Map;
import java.util.Optional;

public interface ManageChatsDAService {
    void createChatWithCreatorAndSimpleMembersAndSaveEvent(
            GroupChat newGroupChat,
            int idCUserCreator,
            int[] idsUsersForAddToChat,
            ChatCreatedEvent event
    );
}
