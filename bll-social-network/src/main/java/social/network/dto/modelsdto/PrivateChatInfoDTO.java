package social.network.dto.modelsdto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import social.network.entities.chat.chatimpl.privatechat.PrivateChatInfo;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrivateChatInfoDTO {
    private String name;

    public PrivateChatInfo getPrivateChatInfo() {
        return new PrivateChatInfo(name);
    }
}
