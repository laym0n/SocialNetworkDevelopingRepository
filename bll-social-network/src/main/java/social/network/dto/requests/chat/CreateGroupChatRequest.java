package social.network.dto.requests.chat;

import lombok.Data;
import social.network.entities.chat.chatimpl.groupchat.GroupChatProfile;

@Data
public class CreateGroupChatRequest {
    private int idUserSenderRequest;
    private GroupChatProfile chatProfile;
    private int[] simpleUsersIds;
}
