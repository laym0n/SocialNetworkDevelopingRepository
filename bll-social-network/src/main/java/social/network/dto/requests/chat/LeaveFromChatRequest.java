package social.network.dto.requests.chat;

import lombok.Data;

@Data
public class LeaveFromChatRequest {
    private int idUserSenderRequest;
    private int idChat;
}
