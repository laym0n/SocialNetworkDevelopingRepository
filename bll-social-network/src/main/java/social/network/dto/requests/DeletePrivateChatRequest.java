package social.network.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeletePrivateChatRequest {
    private int idUserSenderRequest;
    private int idPrivateChat;
}
