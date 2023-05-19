package social.network.dto.requests;

import social.network.dto.modelsdto.messages.MessageDTO;
import lombok.Data;

@Data
public class CreateDialogChatRequest {
    private int idUserSenderRequest;
    private int idUserToDialog;
    private MessageDTO messageDTO;
}
