package social.network.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import social.network.entities.chat.chatimpl.groupchat.GroupChatDescription;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDescriptionGroupChatRequest {
    private int idUserSenderRequest;
    private int idChat;
    private GroupChatDescription newDescription;
}
