package social.network.dto.requests.chat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadAllChatRequest {
    private int idUserOwnerRequest;
    private int idChat;
}
