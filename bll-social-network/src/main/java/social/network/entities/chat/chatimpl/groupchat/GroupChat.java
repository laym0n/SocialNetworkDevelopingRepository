package social.network.entities.chat.chatimpl.groupchat;

import social.network.entities.chat.Chat;
import social.network.entities.chat.chatmemberinfo.GroupChatMember;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class GroupChat extends Chat {
    private GroupChatInfo groupChatInfo;
    private List<GroupChatMember> members = new ArrayList<>();

    public GroupChat(GroupChatInfo groupChatInfo) {
        this.groupChatInfo = groupChatInfo;
    }
}
