package social.network.dto.responses;

import social.network.entities.chat.chatimpl.groupchat.GroupChat;
import lombok.Data;

import java.util.List;

@Data
public class GetChatsResponse {
    public GetChatsResponse(List<GroupChat> groupChatForUser) {
    }
}
