package social.network.dto.requests;

import lombok.Data;
import social.network.entities.chat.messages.Message;

@Data
public class CreateDialogChatRequest {
    private int idUserSenderRequest;
    private int idUserToDialog;
    private Message message;
}
