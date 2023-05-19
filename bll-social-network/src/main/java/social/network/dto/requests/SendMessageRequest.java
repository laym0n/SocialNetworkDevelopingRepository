package social.network.dto.requests;

import social.network.dto.modelsdto.messages.MessageDTO;
import lombok.Data;

@Data
public class SendMessageRequest {
    private MessageDTO messageDTO;
    private int chatId;
}
