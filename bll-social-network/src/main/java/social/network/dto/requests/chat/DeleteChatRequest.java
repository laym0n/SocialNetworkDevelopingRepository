package social.network.dto.requests.chat;

import lombok.Data;

@Data
public class DeleteChatRequest {
    private int idOwnerChat;
    private int idChat;
}
