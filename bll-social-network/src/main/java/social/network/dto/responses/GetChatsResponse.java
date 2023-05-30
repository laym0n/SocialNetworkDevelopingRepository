package social.network.dto.responses;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import social.network.dto.ChatDescriptionDTO;
import social.network.entities.chat.chatimpl.groupchat.GroupChat;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetChatsResponse {
    private List<ChatDescriptionDTO> chats;
}
