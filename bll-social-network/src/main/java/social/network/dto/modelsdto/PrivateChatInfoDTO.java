package social.network.dto.modelsdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import social.network.entities.chat.chatimpl.privatechat.PrivateChatDescription;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrivateChatInfoDTO {
    private String name;
    private byte[] avatar;

    public PrivateChatDescription getPrivateChatInfo() {
//        return new ChatInfo(name, Optional.ofNullable(avatar));
        return null;
    }
}
