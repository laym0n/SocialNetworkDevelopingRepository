package social.network.entities.chat.chatmemberinfo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import social.network.entities.chat.chatimpl.groupchat.ChatMemberRole;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupChatMember extends UserChatMemberProfile {
    private GroupChatMemberProfile chatMemberProfile;
    private Map<Integer, ChatMemberRole> roles;
    private boolean isActiveMember = true;
}
