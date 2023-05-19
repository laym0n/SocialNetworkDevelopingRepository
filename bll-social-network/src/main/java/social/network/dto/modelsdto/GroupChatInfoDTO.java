package social.network.dto.modelsdto;

import social.network.entities.chat.chatimpl.groupchat.GroupChatInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupChatInfoDTO {
    private String chatTitle;
    private byte[] avatar;

    public GroupChatInfo getChatInfo() {
        return new GroupChatInfo(chatTitle, avatar);
    }
}
