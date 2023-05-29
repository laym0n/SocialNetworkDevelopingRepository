package social.network.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;
import social.network.entities.chat.chatimpl.privatechat.PrivateChatDescription;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateDescriptionPrivateChatRequest {
    private int idUserSenderRequest;
    private int chatId;
    private PrivateChatDescription newDescription;
}
