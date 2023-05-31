package social.network.entities.chat.chatimpl.privatechat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import social.network.entities.chat.Chat;
import social.network.entities.chat.ChatMember;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrivateChat extends Chat {
    private ChatMember ownerChat;
    private PrivateChatProfile profile;
}
