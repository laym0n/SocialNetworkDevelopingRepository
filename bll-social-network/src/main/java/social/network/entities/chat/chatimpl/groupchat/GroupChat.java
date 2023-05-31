package social.network.entities.chat.chatimpl.groupchat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import social.network.entities.chat.Chat;
import social.network.entities.chat.chatmemberinfo.GroupChatMember;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupChat extends Chat {
    private GroupChatProfile chatProfile;
    private List<GroupChatMember> members = new ArrayList<>();
    private boolean isBlocked = false;

    public GroupChat(GroupChatProfile chatProfile) {
        this.chatProfile = chatProfile;
    }
}
