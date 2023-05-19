package social.network.daservices;

import social.network.entities.chat.chatmemberinfo.GroupChatMemberProfile;
import social.network.entities.chat.changeshistory.chatchangeevents.ChatCreatedEvent;
import social.network.entities.chat.changeshistory.chatchangeevents.ChatInfoChangedEvent;
import social.network.entities.chat.changeshistory.chatchangeevents.ChatMemberChangedInfoEvent;
import social.network.entities.chat.chatimpl.groupchat.ChatMemberRole;
import social.network.entities.chat.chatimpl.groupchat.GroupChat;
import social.network.entities.chat.chatimpl.groupchat.GroupChatInfo;

import java.util.Map;
import java.util.Optional;

public interface ManageChatsDAService {
    void createChatWithCreatorAndSimpleMembersAndSaveEvent(
            GroupChat newGroupChat,
            int idCUserCreator,
            int[] idsUsersForAddToChat,
            ChatCreatedEvent event
    );


    void addSimpleMemberByUserIdAndSaveEvent(int idNewMember, int idChat);

    Map<Integer, ChatMemberRole> setNotActiveChatMemberAndAddEvent(int idMemberForDelete);

    void updateChatMemberInfoAndAddEvent(GroupChatMemberProfile newChatMemberRoles, int idChatMemberForEdit, int idChat, ChatMemberChangedInfoEvent event);

    void updateGroupChatInfoAndSaveEvent(GroupChatInfo chatInfo, int idChat, ChatInfoChangedEvent event);

    Map<Integer, ChatMemberRole> findRolesForChatMember(int idChat, int idMemberRequestOwner);

    Optional<Integer> makeAnyAdminOwner(int idChat);

    Optional<Integer> makeAnyMemberOwner(int idChat);

    void deleteChat(int idChat);

    void saveChatMemberChangedInfoEvent(ChatMemberChangedInfoEvent eventUpdateInfo);
}
