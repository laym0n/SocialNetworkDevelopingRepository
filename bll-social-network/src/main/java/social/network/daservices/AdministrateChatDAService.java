package social.network.daservices;

import social.network.entities.administrateentities.Ban;
import social.network.entities.chat.changeshistory.chatchangeevents.ChatMemberBlockedEvent;
import social.network.entities.chat.changeshistory.chatchangeevents.ChatMemberChangedInfoEvent;
import social.network.entities.chat.changeshistory.chatchangeevents.ChatMessageBlockedEvent;
import social.network.entities.chat.chatimpl.groupchat.ChatMemberRole;

import java.util.Map;

public interface AdministrateChatDAService {
    void blockMessageAndSaveEvent(Ban messageBan, int idChat, int orderIdMessage, ChatMessageBlockedEvent event);

    void blockChatMemberAndSaveEvent(Ban chatMemberBan, int idChat, int idUser, ChatMemberBlockedEvent event);

    void updateRolesForChatMemberAndSaveEvent(int idChatMemberForChangeRoles, int chatId, Map<Integer, ChatMemberRole> newRoles, ChatMemberChangedInfoEvent event);

    Map<Integer, ChatMemberRole> findRolesOfChatMember(int idUserSenderRequest, int chatId);
}
