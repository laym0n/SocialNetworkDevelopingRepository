package social.network.daservices;

import social.network.entities.chat.chatimpl.groupchat.ChatMemberRole;
import social.network.entities.user.UserRole;

import java.util.Map;

public interface CheckingAccessRightsByRolesDAService {
    Map<Integer, ChatMemberRole> findChatMemberRoles(int idChat, int idMemberOwnerRequest);

    Map<Integer, UserRole> findUserRoles(int idFirst);
}
