package social.network.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import social.network.entities.chat.messages.Message;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDialogChatRequest {
    private int idUserSenderRequest;
    private int idUserToDialog;
    private Message message;
}
