package social.network.entities.chat.chatimpl.groupchat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupChatInfo {
    private String chatTitle;
    private byte[] avatar;
}
