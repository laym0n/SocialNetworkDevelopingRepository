package social.network.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;
import social.network.entities.chat.chatimpl.privatechat.PrivateChatProfile;
import social.network.entities.chat.chatimpl.privatechat.PrivateChat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreatePrivateChatRequest {
    private int idUserSenderRequest;
    private PrivateChatProfile newPrivateChatProfile;
    public PrivateChat getPrivateChat(){
        return new PrivateChat();
    }
}
