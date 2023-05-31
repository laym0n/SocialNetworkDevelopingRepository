package social.network.entities.chat.chatimpl.groupchat;

import social.network.entities.chat.ChatMember;

import java.util.Map;

public class GroupChatMember extends ChatMember {
    private GroupChatMemberProfile chatMemberProfile;
    private final boolean isBlocked = false;
    private Map<Integer, ChatMemberRole> roles;
}
