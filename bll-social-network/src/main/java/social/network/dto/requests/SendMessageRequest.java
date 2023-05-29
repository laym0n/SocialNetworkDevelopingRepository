package social.network.dto.requests;

import social.network.dto.modelsdto.messages.MessageDTO;
import lombok.Data;
import social.network.entities.chat.messages.SimpleMessage;

@Data
public class SendMessageRequest {
    private int idUserOwnerRequest;
    private int chatId;
    private SimpleMessage simpleMessage;
}
