package social.network.dto.requests;

import social.network.dto.modelsdto.messages.MessageDTO;
import lombok.Data;

@Data
public class EditMessageRequest {
    private int userId;
    private MessageDTO messageDTO;
    private int idChat;
}
