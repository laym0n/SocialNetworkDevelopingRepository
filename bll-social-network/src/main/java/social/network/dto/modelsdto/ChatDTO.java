package social.network.dto.modelsdto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import social.network.dto.ChatDescriptionDTO;
import social.network.dto.modelsdto.messages.MessageDTO;
import social.network.entities.chat.chatimpl.groupchat.GroupChat;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatDTO {
    private List<MessageDTO> messages;
    private ChatDescriptionDTO description;
    private boolean isFrozen;

    public ChatDTO(GroupChat groupChat) {
    }
}
